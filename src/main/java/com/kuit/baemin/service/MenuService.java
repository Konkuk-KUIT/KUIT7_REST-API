package com.kuit.baemin.service;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.Restaurant.RestaurantStatus;
import com.kuit.baemin.domain.menu.Menu;
import com.kuit.baemin.domain.menu.MenuOptionStatus;
import com.kuit.baemin.domain.menu.MenuStatus;
import com.kuit.baemin.dto.response.MenuDetailRes;
import com.kuit.baemin.dto.response.MenuOptionRes;
import com.kuit.baemin.dto.response.MenuRes;
import com.kuit.baemin.exception.GeneralException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.MenuOptionRepository;
import com.kuit.baemin.repository.MenuRepository;
import com.kuit.baemin.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    private final MenuOptionRepository menuOptionRepository;

    public List<MenuRes> getRestaurantMenus(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND));

        if (restaurant.getStatus() != RestaurantStatus.ACTIVE) {
            throw new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND);
        }

        return menuRepository.findByRestaurantIdAndStatus(restaurantId, MenuStatus.ACTIVE)
                .stream()
                .map(MenuRes::from)
                .toList();
    }

    public MenuDetailRes getMenu(Long menuId) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MENU_NOT_FOUND));

        if (menu.getStatus() != MenuStatus.ACTIVE) {
            throw new GeneralException(ErrorStatus.MENU_NOT_FOUND);
        }

        List<MenuOptionRes> options = menuOptionRepository.findByMenuIdAndStatus(menuId, MenuOptionStatus.ACTIVE)
                .stream()
                .map(MenuOptionRes::from)
                .toList();

        return MenuDetailRes.of(menu, options);
    }
}
