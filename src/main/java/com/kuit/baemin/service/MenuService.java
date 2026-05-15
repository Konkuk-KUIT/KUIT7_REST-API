package com.kuit.baemin.service;

import com.kuit.baemin.domain.Restaurant.Menu;
import com.kuit.baemin.domain.Restaurant.MenuStatus;
import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.dto.request.MenuReq;
import com.kuit.baemin.dto.response.MenuRes;
import com.kuit.baemin.exception.GeneralException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.MenuRepository;
import com.kuit.baemin.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    // API 8: 메뉴 추가
    @Transactional
    public Long createMenu(Long restaurantId, MenuReq req) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND));

        Menu menu = Menu.builder()
                .restaurant(restaurant)
                .name(req.getName())
                .price(req.getPrice())
                .description(req.getDescription())
                .status(MenuStatus.ACTIVE)
                .build();

        return menuRepository.save(menu).getId();
    }

    // API 9: 메뉴 목록 조회
    public Page<MenuRes> getMenus(Long restaurantId, Pageable pageable) {
        restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND));

        return menuRepository.findByRestaurantId(restaurantId, pageable)
                .map(MenuRes::from);
    }
}