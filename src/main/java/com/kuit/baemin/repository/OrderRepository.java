package com.kuit.baemin.repository;

import com.kuit.baemin.domain.order.Order;
import com.kuit.baemin.domain.order.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findAllByMember_IdAndStatus(Long memberId, OrderStatus status, Pageable pageable);
}
