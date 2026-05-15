package com.kuit.baemin.service;

import com.kuit.baemin.domain.Restaurant.*;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.member.UserAddress;
import com.kuit.baemin.dto.request.OrderItemReq;
import com.kuit.baemin.dto.request.OrderReq;
import com.kuit.baemin.dto.response.OrderRes;
import com.kuit.baemin.exception.GeneralException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    private final UserAddressRepository userAddressRepository;

    // API 10: 주문 생성
    @Transactional
    public Long createOrder(Long memberId, OrderReq req) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        Restaurant restaurant = restaurantRepository.findById(req.getRestaurantId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND));

        UserAddress address = userAddressRepository.findById(req.getAddressId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.ADDRESS_NOT_FOUND));

        Order order = Order.builder()
                .member(member)
                .restaurant(restaurant)
                .address(address)
                .status(OrderStatus.PENDING)
                .build();

        Order savedOrder = orderRepository.save(order);

        for (OrderItemReq itemReq : req.getOrderItems()) {
            Menu menu = menuRepository.findById(itemReq.getMenuId())
                    .orElseThrow(() -> new GeneralException(ErrorStatus.MENU_NOT_FOUND));

            OrderItem orderItem = OrderItem.builder()
                    .order(savedOrder)
                    .menu(menu)
                    .quantity(itemReq.getQuantity())
                    .price(menu.getPrice())  // 주문 당시 가격 스냅샷
                    .status(MenuStatus.ACTIVE)
                    .build();

            savedOrder.getOrderItems().add(orderItem);
        }

        return savedOrder.getId();
    }

    // 주문 단건 조회
    public OrderRes getOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.ORDER_NOT_FOUND));
        return OrderRes.from(order);
    }
}