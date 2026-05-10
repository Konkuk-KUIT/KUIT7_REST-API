package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
  List<Order> findByMemberId(Long memberId);
  Optional<Order> findByIdAndMemberId(Long id, Long memberId);
}
