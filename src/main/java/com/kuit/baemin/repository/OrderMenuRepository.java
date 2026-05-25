package com.kuit.baemin.repository;

import com.kuit.baemin.domain.OrderMenu.OrderMenu;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderMenuRepository extends JpaRepository<OrderMenu, Long> {

    // 특정 주문의 모든 메뉴 목록 조회
    @EntityGraph(attributePaths = {"menu"})
    List<OrderMenu> findByOrderId(Long orderId);
}