package com.kuit.baemin.service;

import static com.kuit.baemin.exception.errorcode.ErrorStatus.FORBIDDEN;
import static com.kuit.baemin.exception.errorcode.ErrorStatus.MEMBER_NOT_FOUND;
import static com.kuit.baemin.exception.errorcode.ErrorStatus.ORDER_NOT_FOUND;
import static com.kuit.baemin.exception.errorcode.ErrorStatus.RESTAURANT_NOT_FOUND;
import static com.kuit.baemin.exception.errorcode.ErrorStatus.ADDRESS_NOT_FOUND;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.address.Address;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.order.DeliveryOrder;
import com.kuit.baemin.domain.order.OrderMenu;
import com.kuit.baemin.domain.order.OrderMenuOption;
import com.kuit.baemin.domain.order.OrderRecordStatus;
import com.kuit.baemin.domain.order.OrderStatus;
import com.kuit.baemin.dto.request.OrderCreateReq;
import com.kuit.baemin.dto.request.OrderCreateReq.OrderItemOptionReq;
import com.kuit.baemin.dto.request.OrderCreateReq.OrderItemReq;
import com.kuit.baemin.dto.response.OrderCreateRes;
import com.kuit.baemin.dto.response.OrderDetailRes;
import com.kuit.baemin.exception.MemberException;
import com.kuit.baemin.exception.OrderException;
import com.kuit.baemin.repository.AddressRepository;
import com.kuit.baemin.repository.DeliveryOrderRepository;
import com.kuit.baemin.repository.MemberRepository;
import com.kuit.baemin.repository.RestaurantRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final DeliveryOrderRepository deliveryOrderRepository;
    private final MemberRepository memberRepository;
    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;

    @Transactional
    public OrderCreateRes createOrder(OrderCreateReq req) {
        Member member = memberRepository.findById(req.getMemberId())
                .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));
        Restaurant restaurant = restaurantRepository.findById(req.getRestaurantId())
                .orElseThrow(() -> new OrderException(RESTAURANT_NOT_FOUND));
        Address address = addressRepository.findById(req.getAddressId())
                .orElseThrow(() -> new OrderException(ADDRESS_NOT_FOUND));
        if (!address.getMember().getId().equals(member.getId())) {
            throw new OrderException(FORBIDDEN);
        }

        DeliveryOrder order = DeliveryOrder.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderStatus(OrderStatus.ordered)
                .requestMessage(req.getRequestMessage())
                .deliveryFee(req.getDeliveryFee())
                .deliveryRoadAddress(resolveDeliveryRoadAddress(req, address))
                .deliveryDetailAddress(resolveDeliveryDetailAddress(req, address))
                .status(OrderRecordStatus.active)
                .member(member)
                .restaurant(restaurant)
                .address(address)
                .build();

        req.getOrderItems().forEach(itemReq -> order.addOrderMenu(createOrderMenu(itemReq)));

        return OrderCreateRes.from(deliveryOrderRepository.save(order));
    }

    public OrderDetailRes getOrder(Long orderId) {
        DeliveryOrder order = deliveryOrderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(ORDER_NOT_FOUND));
        return OrderDetailRes.from(order);
    }

    private OrderMenu createOrderMenu(OrderItemReq itemReq) {
        OrderMenu orderMenu = OrderMenu.builder()
                .menuId(itemReq.getMenuId())
                .menuName(itemReq.getMenuName())
                .unitPrice(itemReq.getUnitPrice())
                .quantity(itemReq.getQuantity())
                .status(OrderRecordStatus.active)
                .build();

        if (itemReq.getOptions() != null) {
            itemReq.getOptions().forEach(optionReq -> orderMenu.addOrderMenuOption(createOrderMenuOption(optionReq)));
        }

        return orderMenu;
    }

    private OrderMenuOption createOrderMenuOption(OrderItemOptionReq optionReq) {
        return OrderMenuOption.builder()
                .menuOptionId(optionReq.getMenuOptionId())
                .optionGroupName(optionReq.getOptionGroupName())
                .optionName(optionReq.getOptionName())
                .additionalPrice(optionReq.getAdditionalPrice())
                .status(OrderRecordStatus.active)
                .build();
    }

    private String resolveDeliveryRoadAddress(OrderCreateReq req, Address address) {
        if (hasText(req.getDeliveryRoadAddress())) {
            return req.getDeliveryRoadAddress();
        }
        return address.getRoadAddress();
    }

    private String resolveDeliveryDetailAddress(OrderCreateReq req, Address address) {
        if (hasText(req.getDeliveryDetailAddress())) {
            return req.getDeliveryDetailAddress();
        }
        return address.getDetailAddress();
    }

    private boolean hasText(String value) {
        return value != null && !value.isBlank();
    }
}
