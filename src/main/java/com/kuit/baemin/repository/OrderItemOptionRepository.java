package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Restaurant.OrderItemOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemOptionRepository extends JpaRepository<OrderItemOption, Long> {
    List<OrderItemOption> findByOrderItemId(Long orderItemId);
}
