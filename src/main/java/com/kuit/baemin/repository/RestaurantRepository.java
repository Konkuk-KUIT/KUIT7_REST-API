package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    // 가게 목록 조회 (페이징 적용)
    Page<Restaurant> findAll(Pageable pageable);
}