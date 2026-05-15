package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Store.Menu;
import com.kuit.baemin.domain.Store.MenuStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    // 특정 가게의 메뉴 목록 조회 (N+1 방지를 위해 필요시 fetch join 사용 가능)
    List<Menu> findByStore_StoreIdAndStatus(Long storeId, MenuStatus status);
}
