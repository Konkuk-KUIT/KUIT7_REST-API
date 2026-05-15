package com.kuit.baemin.repository;

import com.kuit.baemin.domain.order.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    // 내 주문 내역 조회 (페이징 적용)
    Page<Orders> findByMemberId(Long memberId, Pageable pageable);
}