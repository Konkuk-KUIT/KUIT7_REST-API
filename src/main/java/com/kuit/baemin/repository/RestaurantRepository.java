package com.kuit.baemin.repository;

import com.kuit.baemin.domain.restaurant.Restaurant;
import com.kuit.baemin.domain.restaurant.RestaurantStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findAllByCategoryIdAndStatus(
            Long categoryId,
            RestaurantStatus status
    );

    List<Restaurant> findAllByDeliveryFeeLessThanEqualAndStatus(
            BigDecimal fee,
            RestaurantStatus status
    );

}
