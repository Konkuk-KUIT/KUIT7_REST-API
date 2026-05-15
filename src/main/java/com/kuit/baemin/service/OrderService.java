package com.kuit.baemin.service;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.Restaurant.RestaurantStatus;
import com.kuit.baemin.domain.address.Address;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.order.OrderEntity;
import com.kuit.baemin.dto.request.CreateOrderReq;
import com.kuit.baemin.dto.response.CreateOrderRes;
import com.kuit.baemin.exception.GeneralException;
import com.kuit.baemin.exception.MemberException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.AddressRepository;
import com.kuit.baemin.repository.MemberRepository;
import com.kuit.baemin.repository.OrderRepository;
import com.kuit.baemin.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kuit.baemin.exception.errorcode.ErrorStatus.MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;

    @Transactional
    public CreateOrderRes createOrder(CreateOrderReq req) {
        Member member = memberRepository.findById(req.getUserId())
                .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));
        Restaurant restaurant = restaurantRepository.findById(req.getStoreId())

                .orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));

        Address address = addressRepository.findById(req.getAddressId())

                .orElseThrow(() -> new GeneralException(ErrorStatus.ADDRESS_NOT_FOUND));

        if (restaurant.getStatus() != RestaurantStatus.ACTIVE) {
            throw new GeneralException(ErrorStatus.STORE_NOT_AVAILABLE);
        }
        if (address.isDeleted()) {
            throw new GeneralException(ErrorStatus.ADDRESS_ALREADY_DELETED);
        }
        if (!address.getMember().getId().equals(member.getId())) {
            throw new GeneralException(ErrorStatus.ADDRESS_NOT_FOUND);
        }
        Integer deliveryFee = restaurant.getDeliveryFee();
        /*
         * 현재 명세 Request에는 주문 메뉴 목록(items)이 없어서
         * 실제 메뉴 합계 금액을 계산할 수 없음.
         * 그래서 임시로 최소 주문 금액을 주문 금액으로 사용.
         * 나중에 items를 추가하면 메뉴 가격 * 수량 + 옵션 가격으로 totalPrice 계산하면 됨.
         */
        Integer totalPrice = restaurant.getMinOrderPrice() + deliveryFee;
        if (totalPrice < restaurant.getMinOrderPrice()) {
            throw new GeneralException(ErrorStatus.ORDER_MIN_PRICE_NOT_MET);
        }
        OrderEntity order = OrderEntity.builder()
                .orderNumber(generateOrderNumber())
                .totalPrice(totalPrice)
                .deliveryFee(deliveryFee)
                .requestMessage(req.getRequestMessage())
                .member(member)
                .restaurant(restaurant)
                .address(address)
                .build();
        OrderEntity savedOrder = orderRepository.save(order);
        return CreateOrderRes.from(savedOrder);
    }
    private String generateOrderNumber() {
        return "ORD-" + System.currentTimeMillis();
    }
}
