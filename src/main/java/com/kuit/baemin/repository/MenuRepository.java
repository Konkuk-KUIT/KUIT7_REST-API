package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.menu.Menu;
import com.kuit.baemin.domain.menu.MenuStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    Page<Menu> findAllByRestaurantAndStatus(Restaurant restaurant, MenuStatus status, Pageable pageable);

    Optional<Menu> findByIdAndRestaurantAndStatus(Long id, Restaurant restaurant, MenuStatus status);
}
