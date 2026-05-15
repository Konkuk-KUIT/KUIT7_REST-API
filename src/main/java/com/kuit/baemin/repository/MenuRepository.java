package com.kuit.baemin.repository;

import com.kuit.baemin.domain.menu.Menu;
import com.kuit.baemin.domain.menu.MenuStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    Page<Menu> findAllByRestaurant_IdAndStatusNot(Long restaurantId, MenuStatus excludedStatus, Pageable pageable);
}
