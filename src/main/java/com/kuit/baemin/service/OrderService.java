package com.kuit.baemin.service;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.address.Address;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.menu.Menu;
import com.kuit.baemin.domain.menu.MenuOption;
import com.kuit.baemin.domain.order.*;
import com.kuit.baemin.dto.request.OrderCreateReq;
import com.kuit.baemin.dto.request.OrderItemCreateReq;
import com.kuit.baemin.dto.response.OrderItemOptionRes;
import com.kuit.baemin.dto.response.OrderItemRes;
import com.kuit.baemin.dto.response.OrderRes;
import com.kuit.baemin.exception.GeneralException;
import com.kuit.baemin.exception.MemberException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderItemOptionRepository orderItemOptionRepository;
    private final MemberRepository memberRepository;
    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;
    private final MenuRepository menuRepository;
    private final MenuOptionRepository menuOptionRepository;

    @Transactional
    public Long createOrder(OrderCreateReq req) {
        Member member = memberRepository.findById(req.getMemberId())
                .orElseThrow(() -> new MemberException(ErrorStatus.MEMBER_NOT_FOUND));

        Restaurant restaurant = restaurantRepository.findById(req.getRestaurantId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND));

        Address address = addressRepository.findById(req.getAddressId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.ADDRESS_NOT_FOUND));

        Orders order = Orders.builder()
                .member(member)
                .restaurant(restaurant)
                .address(address)
                .orderStatus(OrderStatus.ORDERED)
                .status(OrderItemStatus.ACTIVE)
                .build();

        Orders savedOrder = orderRepository.save(order);

        for (OrderItemCreateReq itemReq : req.getItems()) {
            Menu menu = menuRepository.findById(itemReq.getMenuId())
                    .orElseThrow(() -> new GeneralException(ErrorStatus.MENU_NOT_FOUND));

            OrderItem orderItem = OrderItem.builder()
                    .order(savedOrder)
                    .menu(menu)
                    .menuName(menu.getName())
                    .menuPrice(menu.getPrice())
                    .quantity(itemReq.getQuantity())
                    .status(OrderItemStatus.ACTIVE)
                    .build();

            OrderItem savedOrderItem = orderItemRepository.save(orderItem);

            List<Long> optionIds = itemReq.getOptionIds();
            if (optionIds == null) {
                optionIds = Collections.emptyList();
            }

            for (Long optionId : optionIds) {
                MenuOption menuOption = menuOptionRepository.findById(optionId)
                        .orElseThrow(() -> new GeneralException(ErrorStatus.MENU_OPTION_NOT_FOUND));

                OrderItemOption orderItemOption = OrderItemOption.builder()
                        .orderItem(savedOrderItem)
                        .menuOption(menuOption)
                        .optionGroup(menuOption.getOptionGroup())
                        .optionName(menuOption.getOptionName())
                        .additionalPrice(menuOption.getAdditionalPrice())
                        .status(OrderItemOptionStatus.ACTIVE)
                        .build();

                orderItemOptionRepository.save(orderItemOption);
            }
        }

        return savedOrder.getId();
    }

    public Page<OrderRes> getMemberOrders(Long memberId, Pageable pageable) {
        if (!memberRepository.existsById(memberId)) {
            throw new MemberException(ErrorStatus.MEMBER_NOT_FOUND);
        }

        return orderRepository.findByMemberIdAndStatus(memberId, OrderItemStatus.ACTIVE, pageable)
                .map(this::toOrderRes);
    }

    private OrderRes toOrderRes(Orders order) {
        List<OrderItem> orderItems = orderItemRepository.findByOrderIdAndStatus(
                order.getId(),
                OrderItemStatus.ACTIVE
        );

        List<OrderItemRes> itemResponses = orderItems.stream()
                .map(item -> {
                    List<OrderItemOptionRes> optionResponses =
                            orderItemOptionRepository.findByOrderItemIdAndStatus(
                                            item.getId(),
                                            OrderItemOptionStatus.ACTIVE
                                    )
                                    .stream()
                                    .map(OrderItemOptionRes::from)
                                    .toList();

                    return OrderItemRes.of(item, optionResponses);
                })
                .toList();

        Integer totalPrice = calculateTotalPrice(orderItems);

        return OrderRes.of(order, totalPrice, itemResponses);
    }

    private Integer calculateTotalPrice(List<OrderItem> orderItems) {
        int total = 0;

        for (OrderItem item : orderItems) {
            List<OrderItemOption> options = orderItemOptionRepository.findByOrderItemIdAndStatus(
                    item.getId(),
                    OrderItemOptionStatus.ACTIVE
            );

            int optionTotal = options.stream()
                    .mapToInt(OrderItemOption::getAdditionalPrice)
                    .sum();

            total += (item.getMenuPrice() + optionTotal) * item.getQuantity();
        }

        return total;
    }
}
