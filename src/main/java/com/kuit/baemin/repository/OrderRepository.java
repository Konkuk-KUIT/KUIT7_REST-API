package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

  // 회원 ID로 주문 조회
  List<Order> findByMemberId(Long memberId);

  // 주문 ID와 회원 ID로 조회
  Optional<Order> findByIdAndMemberId(Long id, Long memberId);
}
