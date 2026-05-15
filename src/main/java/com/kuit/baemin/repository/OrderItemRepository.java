package com.kuit.baemin.repository;

import com.kuit.baemin.domain.order.OrderItem;
import com.kuit.baemin.domain.order.OrderItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    List<OrderItem> findByOrderIdAndStatus(Long orderId, OrderItemStatus status);
}
