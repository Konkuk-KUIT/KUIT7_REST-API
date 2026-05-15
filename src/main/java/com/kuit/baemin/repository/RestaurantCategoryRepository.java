package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Restaurant.RestaurantCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantCategoryRepository extends JpaRepository<RestaurantCategory, Long> {
    List<RestaurantCategory> findByRestaurantId(Long restaurantId);
}
