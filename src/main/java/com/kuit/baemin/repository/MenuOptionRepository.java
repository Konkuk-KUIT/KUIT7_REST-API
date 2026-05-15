package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Restaurant.MenuOption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuOptionRepository extends JpaRepository<MenuOption, Long> {
    List<MenuOption> findByOptionGroupId(Long optionGroupId);
}
