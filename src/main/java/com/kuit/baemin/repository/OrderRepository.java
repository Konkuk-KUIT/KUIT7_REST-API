package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Order.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @NonNull
    @EntityGraph(attributePaths = {"user", "restaurant", "address"})
    Optional<Order> findById(@NonNull Long id);

}