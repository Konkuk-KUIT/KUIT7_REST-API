package com.kuit.baemin.service;

import com.kuit.baemin.domain.address.Address;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.menu.Menu;
import com.kuit.baemin.domain.order.Order;
import com.kuit.baemin.domain.orderitem.OrderItem;
import com.kuit.baemin.domain.restaurant.Restaurant;
import com.kuit.baemin.dto.request.CreateOrderReq;
import com.kuit.baemin.dto.response.OrderRes;
import com.kuit.baemin.exception.*;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final RestaurantRepository restaurantRepository;
    private final AddressRepository addressRepository;
    private final MenuRepository menuRepository;

    @Transactional
    public OrderRes createOrder(CreateOrderReq req) {
        Member member = memberRepository.findById(req.getMemberId())
                .orElseThrow(() -> new MemberException(ErrorStatus.MEMBER_NOT_FOUND));
        Restaurant restaurant = restaurantRepository.findById(req.getRestaurantId())
                .orElseThrow(() -> new RestaurantException(ErrorStatus.RESTAURANT_NOT_FOUND));
        Address address = addressRepository.findById(req.getAddressId())
                .orElseThrow(() -> new AddressException(ErrorStatus.ADDRESS_NOT_FOUND));

        // OrderItem 목록 생성 및 총 금액 계산
        List<OrderItem> orderItems = new ArrayList<>();
        int totalPrice = 0;

        for (CreateOrderReq.OrderItemReq itemReq : req.getItems()) {
            Menu menu = menuRepository.findById(itemReq.getMenuId())
                    .orElseThrow(() -> new MenuException(ErrorStatus.MENU_NOT_FOUND));
            if (!menu.getIsAvailable()) {
                throw new MenuException(ErrorStatus.MENU_NOT_AVAILABLE);
            }
            int itemTotal = menu.getPrice() * itemReq.getQuantity();
            totalPrice += itemTotal;

            orderItems.add(OrderItem.builder()
                    .menu(menu)
                    .quantity(itemReq.getQuantity())
                    .unitPrice(menu.getPrice())
                    .build());
        }

        Order order = Order.builder()
                .member(member)
                .restaurant(restaurant)
                .address(address)
                .status("PENDING")
                .totalPrice(totalPrice)
                .paymentMethod(req.getPaymentMethod())
                .requestNote(req.getRequestNote())
                .orderedAt(LocalDateTime.now())
                .build();

        // 연관관계 세팅
        for (OrderItem item : orderItems) {
            order.getOrderItems().add(item);
        }

        return OrderRes.from(orderRepository.save(order));
    }
}