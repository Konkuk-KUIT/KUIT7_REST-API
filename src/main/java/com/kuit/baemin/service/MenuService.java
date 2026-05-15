package com.kuit.baemin.service;

import com.kuit.baemin.domain.menu.Menu;
import com.kuit.baemin.domain.menu.MenuStatus;
import com.kuit.baemin.domain.restaurant.Restaurant;
import com.kuit.baemin.dto.request.MenuCreateReq;
import com.kuit.baemin.dto.response.MenuRes;
import com.kuit.baemin.dto.response.PageRes;
import com.kuit.baemin.exception.GeneralException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.MenuRepository;
import com.kuit.baemin.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Long create(Long restaurantId, MenuCreateReq req) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND));

        Menu menu = Menu.builder()
                .restaurant(restaurant)
                .name(req.getName())
                .description(req.getDescription())
                .price(req.getPrice())
                .status(MenuStatus.AVAILABLE)
                .build();

        return menuRepository.save(menu).getId();
    }

    public PageRes<MenuRes> list(Long restaurantId, Pageable pageable) {
        if (!restaurantRepository.existsById(restaurantId)) {
            throw new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND);
        }
        return PageRes.of(
                menuRepository.findAllByRestaurant_IdAndStatusNot(restaurantId, MenuStatus.DELETED, pageable),
                MenuRes::from
        );
    }
}
