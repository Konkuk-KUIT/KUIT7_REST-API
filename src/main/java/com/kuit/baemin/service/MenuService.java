package com.kuit.baemin.service;

import com.kuit.baemin.domain.menu.Menu;
import com.kuit.baemin.domain.restaurant.Restaurant;
import com.kuit.baemin.dto.request.CreateMenuReq;
import com.kuit.baemin.dto.response.MenuRes;
import com.kuit.baemin.exception.RestaurantException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.MenuRepository;
import com.kuit.baemin.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Long createMenu(Long restaurantId, CreateMenuReq req) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantException(ErrorStatus.RESTAURANT_NOT_FOUND));

        Menu menu = Menu.builder()
                .restaurant(restaurant)
                .menuName(req.getMenuName())
                .description(req.getDescription())
                .price(req.getPrice())
                .isAvailable(true)
                .build();
        return menuRepository.save(menu).getId();
    }

    public List<MenuRes> getMenus(Long restaurantId) {
        restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantException(ErrorStatus.RESTAURANT_NOT_FOUND));
        return menuRepository.findAllByRestaurantId(restaurantId).stream()
                .map(MenuRes::from)
                .collect(Collectors.toList());
    }
}