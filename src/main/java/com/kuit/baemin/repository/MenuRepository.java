package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Menu.Menu;
import com.kuit.baemin.domain.Store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<com.kuit.baemin.domain.Menu.Menu> findAllByStoreAndStatus(Store store, String status);
}
