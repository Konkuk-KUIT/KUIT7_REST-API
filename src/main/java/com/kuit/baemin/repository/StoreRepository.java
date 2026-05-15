package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Store.Store;
import com.kuit.baemin.domain.Store.StoreStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StoreRepository extends JpaRepository<Store, Long> {
    // 카테고리별 가게 목록 조회 (페이징 지원)
    Page<Store> findByStoreCategoryAndStatus(String category, StoreStatus status, Pageable pageable);
}
