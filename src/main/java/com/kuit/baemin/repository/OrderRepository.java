package com.kuit.baemin.repository;

import com.kuit.baemin.domain.order.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
