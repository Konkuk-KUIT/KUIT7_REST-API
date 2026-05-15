package com.kuit.baemin.service;

import com.kuit.baemin.domain.Restaurant.Menu;
import com.kuit.baemin.domain.Restaurant.Order;
import com.kuit.baemin.domain.Restaurant.OrderItem;
import com.kuit.baemin.domain.Restaurant.OrderStatus;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.dto.request.OrderItemReq;
import com.kuit.baemin.dto.request.OrderReq;
import com.kuit.baemin.dto.response.OrderItemRes;
import com.kuit.baemin.dto.response.OrderRes;
import com.kuit.baemin.exception.GeneralException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.MenuRepository;
import com.kuit.baemin.repository.MemberRepository;
import com.kuit.baemin.repository.OrderItemRepository;
import com.kuit.baemin.repository.OrderRepository;
import com.kuit.baemin.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final MenuRepository menuRepository;
    private final MemberRepository memberRepository;
    private final RestaurantRepository restaurantRepository;

    /**
     * API 8: POST /orders — 주문 생성
     */
    @Transactional
    public Long createOrder(Long userId, OrderReq req) {
        Member user = memberRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        restaurantRepository.findById(req.getRestaurantId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND));

        Order order = Order.builder()
                .user(user)
                .restaurant(restaurantRepository.findById(req.getRestaurantId()).get())
                .deliveryAddressId(req.getDeliveryAddressId())
                .orderStatus(OrderStatus.PENDING)
                .requestMemo(req.getRequestMemo())
                .build();

        Order savedOrder = orderRepository.save(order);

        // OrderItem 생성
        for (OrderItemReq itemReq : req.getOrderItems()) {
            Menu menu = menuRepository.findById(itemReq.getMenuId())
                    .orElseThrow(() -> new GeneralException(ErrorStatus.MENU_NOT_FOUND));

            OrderItem orderItem = OrderItem.builder()
                    .order(savedOrder)
                    .menu(menu)
                    .menuNameSnapshot(menu.getName())
                    .menuPriceSnapshot(menu.getPrice())
                    .quantity(itemReq.getQuantity())
                    .status("CONFIRMED")
                    .build();

            orderItemRepository.save(orderItem);
        }

        return savedOrder.getId();
    }

    /**
     * API 9: GET /orders/{orderId} — 주문 상세 조회
     */
    public OrderRes getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.ORDER_NOT_FOUND));

        List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);
        List<OrderItemRes> orderItemResponses = orderItems.stream()
                .map(OrderItemRes::from)
                .toList();

        return OrderRes.builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .restaurantId(order.getRestaurant().getId())
                .deliveryAddressId(order.getDeliveryAddressId())
                .orderStatus(order.getOrderStatus().toString())
                .requestMemo(order.getRequestMemo())
                .orderItems(orderItemResponses)
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }

    /**
     * API 10: GET /users/{userId}/orders — 사용자 주문 목록 조회 (페이징)
     */
    public Page<OrderRes> getOrdersByUser(Long userId, Pageable pageable) {
        memberRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        Page<Order> orders = orderRepository.findByUserId(userId, pageable);
        return orders.map(OrderRes::from);
    }
}
