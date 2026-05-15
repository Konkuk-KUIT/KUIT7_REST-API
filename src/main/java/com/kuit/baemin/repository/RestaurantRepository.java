package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
