package com.kuit.baemin.repository;

import com.kuit.baemin.domain.order.Orders;
import com.kuit.baemin.domain.order.OrderItemStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    Page<Orders> findByMemberIdAndStatus(Long memberId, OrderItemStatus status, Pageable pageable);
}
