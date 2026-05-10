package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.Restaurant.RestaurantStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Restaurant, Long> {
  List<Restaurant> findByStatus(RestaurantStatus status);
  Optional<Restaurant> findByIdAndStatus(Long id, RestaurantStatus status);
}
