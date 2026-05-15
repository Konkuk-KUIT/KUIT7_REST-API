package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Restaurant.MenuOptionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuOptionGroupRepository extends JpaRepository<MenuOptionGroup, Long> {
    List<MenuOptionGroup> findByMenuId(Long menuId);
}
