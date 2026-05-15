package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Restaurant.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    // 특정 가게의 메뉴판 조회
    List<Menu> findByRestaurantId(Long restaurantId);
}