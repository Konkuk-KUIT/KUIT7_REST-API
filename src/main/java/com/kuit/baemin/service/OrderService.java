package com.kuit.baemin.service;

import com.kuit.baemin.domain.Restaurant.Menu;
import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.order.OrderItem;
import com.kuit.baemin.domain.order.Orders;
import com.kuit.baemin.dto.request.OrderItemReq;
import com.kuit.baemin.dto.request.OrderReq;
import com.kuit.baemin.dto.response.OrderDetailRes;
import com.kuit.baemin.dto.response.OrderListRes;
import com.kuit.baemin.exception.OrderException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrdersRepository ordersRepository;
    private final OrderItemRepository orderItemRepository;
    private final MemberRepository memberRepository;
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    /**
     * API 6: 주문하기
     */
    @Transactional
    public Long createOrder(OrderReq req) {
        Member member = memberRepository.findById(req.getMemberId())
                .orElseThrow(() -> new OrderException(ErrorStatus.MEMBER_NOT_FOUND));
        Restaurant restaurant = restaurantRepository.findById(req.getRestaurantId())
                .orElseThrow(() -> new OrderException(ErrorStatus.RESTAURANT_NOT_FOUND));

        // 1. 주문(Orders) 생성 및 저장
        Orders order = Orders.builder()
                .member(member)
                .restaurant(restaurant)
                .totalPrice(req.getTotalPrice())
                .status("ORDERED") // 기본 상태: 주문 완료
                .build();
        Orders savedOrder = ordersRepository.save(order);

        // 2. 주문 상세(OrderItem) 저장
        for (OrderItemReq itemReq : req.getOrderItems()) {
            Menu menu = menuRepository.findById(itemReq.getMenuId())
                    .orElseThrow(() -> new OrderException(ErrorStatus.MENU_NOT_FOUND));

            OrderItem orderItem = OrderItem.builder()
                    .orders(savedOrder)
                    .menu(menu)
                    .quantity(itemReq.getQuantity())
                    .price(itemReq.getPrice())
                    .build();
            orderItemRepository.save(orderItem);
        }

        return savedOrder.getId();
    }

    /**
     * API 7: 내 주문 내역 조회 (페이징)
     */
    public OrderListRes getOrders(Long memberId, Pageable pageable) {
        Page<Orders> orderPage = ordersRepository.findByMemberId(memberId, pageable);

        List<OrderDetailRes> dtoList = orderPage.getContent().stream()
                .map(o -> OrderDetailRes.builder()
                        .orderId(o.getId())
                        .restaurantName(o.getRestaurant().getName())
                        .totalPrice(o.getTotalPrice())
                        .status(o.getStatus())
                        .build())
                .collect(Collectors.toList());

        return OrderListRes.builder()
                .orders(dtoList)
                .currentPage(orderPage.getNumber())
                .build();
    }

    /**
     * API 8: 주문 취소
     */
    @Transactional
    public Long cancelOrder(Long orderId) {
        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(ErrorStatus.ORDER_NOT_FOUND));

        // 배달 중이거나 완료된 주문은 취소 불가 로직 (간단히 예시)
        if ("DELIVERING".equals(order.getStatus()) || "COMPLETED".equals(order.getStatus())) {
            throw new OrderException(ErrorStatus.ORDER_CANNOT_CANCEL);
        }

        order.changeStatus("CANCELLED"); // JPA 더티 체킹 덕분에 자동으로 DB 업데이트 됨
        return order.getId();
    }
}