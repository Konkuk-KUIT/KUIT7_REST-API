package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Order.Order;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("select o from Order o join fetch o.store where o.user.userId = :userId")
    Slice<Order> findByUserIdWithStore(@Param("userId") Long userId, Pageable pageable);
}
