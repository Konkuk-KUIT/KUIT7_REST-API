package com.kuit.baemin.service;

import com.kuit.baemin.domain.menu.Menu;
import com.kuit.baemin.dto.response.MenuRes;
import com.kuit.baemin.exception.MenuException;
import com.kuit.baemin.exception.RestaurantException;
import com.kuit.baemin.repository.MenuRepository;
import com.kuit.baemin.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kuit.baemin.exception.errorcode.ErrorStatus.MENU_NOT_FOUND;
import static com.kuit.baemin.exception.errorcode.ErrorStatus.RESTAURANT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    public List<MenuRes> getMenus(Long restaurantId, String category) {
        // 1. 식당 존재 확인 (404 예외)
        if (!restaurantRepository.existsById(restaurantId)) {
            throw new RestaurantException(RESTAURANT_NOT_FOUND);
        }

        List<Menu> menus;

        // 2. 카테고리별 조회 혹은 전체 조회
        if (category != null && !category.isEmpty()) {
            menus = menuRepository.findByRestaurantIdAndCategory(restaurantId, category);
        } else {
            menus = menuRepository.findByRestaurantId(restaurantId);
        }

        // 3. 결과가 하나도 없을 때 (명세서의 404 조건 반영)
        if (menus.isEmpty()) {
            throw new MenuException(MENU_NOT_FOUND);
        }

        return menus.stream()
                .map(MenuRes::from)
                .toList();
    }
}