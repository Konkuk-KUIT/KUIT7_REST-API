package com.kuit.baemin.service;

import com.kuit.baemin.domain.cart.Cart;
import com.kuit.baemin.domain.cart.CartStatus;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.order.Order;
import com.kuit.baemin.domain.order.OrderStatus;
import com.kuit.baemin.domain.Restaurant.Store;
import com.kuit.baemin.dto.request.OrderReq;
import com.kuit.baemin.repository.CartRepository;
import com.kuit.baemin.repository.MemberRepository;
import com.kuit.baemin.repository.OrderRepository;
import com.kuit.baemin.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final CartRepository cartRepository;

    public Long createOrder(OrderReq req) {
        Member member = memberRepository.findById(req.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Store store = storeRepository.findById(req.getStoreId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 가게입니다."));

        Order order = Order.builder()
                .member(member)
                .store(store)
                .paymentMethod(req.getPaymentMethod())
                .totalPrice(req.getTotalPrice())
                .requests(req.getRequests())
                .status(OrderStatus.ACCEPTED)
                .build();

        Order savedOrder = orderRepository.save(order);

        List<Cart> activeCarts = cartRepository.findByMemberIdAndStatus(member.getId(), CartStatus.ACTIVE);
        for (Cart cart : activeCarts) {
        }

        return savedOrder.getId();
    }
}