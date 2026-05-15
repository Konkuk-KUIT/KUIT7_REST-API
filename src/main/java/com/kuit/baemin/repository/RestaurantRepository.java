package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    // Id로 조회 (단일 가게 조회)
    Optional<Restaurant> findById(Long id);

    // 가게명 중복 확인
    boolean existsByName(String restaurantName);
}
