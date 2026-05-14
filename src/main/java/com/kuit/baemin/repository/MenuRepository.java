package com.kuit.baemin.repository;

import com.kuit.baemin.domain.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    // 1. 음식점 ID로 전체 메뉴 조회
    @Query("SELECT m FROM Menu m JOIN FETCH m.restaurant WHERE m.restaurant.id = :restaurantId")
    List<Menu> findByRestaurantId(@Param("restaurantId") Long restaurantId);

    // 2. 음식점 ID와 카테고리로 필터링 조회
    @Query("SELECT m FROM Menu m JOIN FETCH m.restaurant WHERE m.restaurant.id = :restaurantId AND m.category = :category")
    List<Menu> findByRestaurantIdAndCategory(
            @Param("restaurantId") Long restaurantId,
            @Param("category") String category
    );
}