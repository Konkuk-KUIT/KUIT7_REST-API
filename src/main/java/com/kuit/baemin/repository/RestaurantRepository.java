package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.Restaurant.RestaurantStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Page<Restaurant> findAllByStatus(RestaurantStatus status, Pageable pageable);

    Optional<Restaurant> findByIdAndStatus(Long id, RestaurantStatus status);
}
