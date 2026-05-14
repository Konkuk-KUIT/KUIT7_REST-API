package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {


}
