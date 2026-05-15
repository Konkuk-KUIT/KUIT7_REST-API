package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Restaurant.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Page<Menu> findByRestaurantId(Long restaurantId, Pageable pageable);
}