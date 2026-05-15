package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Store.MenuDetail;
import com.kuit.baemin.domain.Store.MenuStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDetailRepository extends JpaRepository<MenuDetail, Long> {

    List<MenuDetail> findByMenu_MenuIdAndStatus(Long menuId, MenuStatus status);

    boolean existsByMenuDetailIdAndStatus(Long menuDetailId, MenuStatus status);
}
