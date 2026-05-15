package com.kuit.baemin.repository;

import com.kuit.baemin.domain.menu.Menu;
import com.kuit.baemin.domain.menu.MenuStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepositroy extends JpaRepository<Menu, Long> {

    List<Menu> findAllByRestaurantIdAndStatus(Long restaurantId, MenuStatus status);
}
