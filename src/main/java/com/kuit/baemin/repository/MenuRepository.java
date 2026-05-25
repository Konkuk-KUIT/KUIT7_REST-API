package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Menu.Menu;
import com.kuit.baemin.domain.Menu.MenuStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    // 특정 식당의 메뉴 조회
    List<Menu> findByRestaurantIdAndStatus(Long restaurantId, MenuStatus status, Pageable pageable);
}
