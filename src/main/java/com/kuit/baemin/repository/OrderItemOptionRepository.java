package com.kuit.baemin.repository;

import com.kuit.baemin.domain.order.OrderItemOption;
import com.kuit.baemin.domain.order.OrderItemOptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemOptionRepository extends JpaRepository<OrderItemOption, Long> {

    List<OrderItemOption> findByOrderItemIdAndStatus(Long orderItemId, OrderItemOptionStatus status);
}
