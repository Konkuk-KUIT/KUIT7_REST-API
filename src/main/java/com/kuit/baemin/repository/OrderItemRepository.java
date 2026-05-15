package com.kuit.baemin.repository;

import com.kuit.baemin.domain.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    // 주문 상세 항목 저장을 위한 기본 Repository
}