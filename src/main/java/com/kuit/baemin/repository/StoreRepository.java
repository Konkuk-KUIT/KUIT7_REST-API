package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Restaurant.Store;
import com.kuit.baemin.domain.Restaurant.StoreStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
    // 카테고리별 가게 목록을 페이징하여 조회
    Page<Store> findByCategoryAndStatus(String category, StoreStatus status, Pageable pageable);}