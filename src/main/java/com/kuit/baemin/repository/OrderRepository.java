package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Order.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {"user", "restaurant", "address"})
    Optional<Order> findById(Long id);

}