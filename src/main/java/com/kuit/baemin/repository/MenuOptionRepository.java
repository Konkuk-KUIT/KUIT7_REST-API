package com.kuit.baemin.repository;

import com.kuit.baemin.domain.menu.MenuOption;
import com.kuit.baemin.domain.menu.MenuOptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuOptionRepository extends JpaRepository<MenuOption, Long> {

    List<MenuOption> findByMenuIdAndStatus(Long menuId, MenuOptionStatus status);
}
