package com.kuit.baemin.service;

import com.kuit.baemin.domain.Restaurant.Store;
import com.kuit.baemin.domain.Restaurant.StoreStatus;
import com.kuit.baemin.dto.response.StoreListRes;
import com.kuit.baemin.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final StoreRepository storeRepository;

    public Page<StoreListRes> getStoreList(String category, Pageable pageable) {
        Page<Store> storePage = storeRepository.findByCategoryAndStatus(category, StoreStatus.ACTIVE, pageable);
        return storePage.map(StoreListRes::from);
    }
}