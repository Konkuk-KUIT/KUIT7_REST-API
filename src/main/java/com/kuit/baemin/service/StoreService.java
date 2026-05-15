package com.kuit.baemin.service;

import com.kuit.baemin.domain.Store.Menu;
import com.kuit.baemin.domain.Store.MenuStatus;
import com.kuit.baemin.domain.Store.Store;
import com.kuit.baemin.domain.Store.StoreStatus;
import com.kuit.baemin.dto.response.MenuRes;
import com.kuit.baemin.dto.response.StoreRes;
import com.kuit.baemin.exception.GeneralException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.MenuRepository;
import com.kuit.baemin.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;

    /**
     * 2. 가게 목록 조회 (페이징)
     */
    public Page<StoreRes> getStores(String category, Pageable pageable) {
        // ACTIVE 상태인 가게만 조회
        Page<Store> stores = storeRepository
                .findByStoreCategoryAndStatus(category, StoreStatus.ACTIVE, pageable);

        // Entity -> DTO 변환
        return stores.map(StoreRes::from);
    }

    /**
     * 4. 특정 가게의 메뉴 목록 조회
     */
    public List<MenuRes> getMenusByStore(Long storeId) {
        // 가게 존재 여부 확인 (예외 처리 패턴 적용 권장)
        if (!storeRepository.existsById(storeId)) {
            throw new GeneralException(ErrorStatus.STORE_NOT_FOUND);
        }

        List<Menu> menus = menuRepository.findByStore_StoreIdAndStatus(storeId, MenuStatus.ACTIVE);

        return menus.stream()
                .map(MenuRes::from)
                .toList();
    }
}
