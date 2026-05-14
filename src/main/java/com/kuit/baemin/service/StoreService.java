package com.kuit.baemin.service;

import com.kuit.baemin.domain.Restaurant.RestaurantStatus;
import com.kuit.baemin.domain.menu.MenuStatus;
import com.kuit.baemin.dto.response.MenuListRes;
import com.kuit.baemin.dto.response.MenuRes;
import com.kuit.baemin.dto.response.StoreDetailRes;
import com.kuit.baemin.dto.response.StoreListRes;
import com.kuit.baemin.dto.response.StoreRes;
import com.kuit.baemin.exception.StoreException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.MenuRepository;
import com.kuit.baemin.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    public StoreListRes getStores(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return StoreListRes.from(
                restaurantRepository.findAllByStatus(RestaurantStatus.ACTIVE, pageable)
                        .map(StoreRes::from)
        );
    }

    public StoreDetailRes getStore(Long storeId) {
        return restaurantRepository.findByIdAndStatus(storeId, RestaurantStatus.ACTIVE)
                .map(StoreDetailRes::from)
                .orElseThrow(() -> new StoreException(ErrorStatus.STORE_NOT_FOUND));
    }

    public MenuListRes getMenus(Long storeId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var restaurant = restaurantRepository.findByIdAndStatus(storeId, RestaurantStatus.ACTIVE)
                .orElseThrow(() -> new StoreException(ErrorStatus.STORE_NOT_FOUND));

        return MenuListRes.from(
                menuRepository.findAllByRestaurantAndStatus(restaurant, MenuStatus.ACTIVE, pageable)
                        .map(MenuRes::from)
        );
    }
}
