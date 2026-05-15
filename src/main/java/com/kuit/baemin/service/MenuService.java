package com.kuit.baemin.service;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.Restaurant.RestaurantStatus;
import com.kuit.baemin.domain.menu.Menu;
import com.kuit.baemin.domain.menu.MenuStatus;
import com.kuit.baemin.dto.response.MenuListRes;
import com.kuit.baemin.exception.GeneralException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.MenuRepositroy;
import com.kuit.baemin.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {
    private final MenuRepositroy menuRepositroy;
    private final RestaurantRepository retaurantRepository;

    public MenuListRes getMenus(Long storeId){
        Restaurant restaurant = retaurantRepository.findById(storeId)
        .orElseThrow(()-> new GeneralException(ErrorStatus.STORE_NOT_FOUND));

        if(restaurant.getStatus() != RestaurantStatus.ACTIVE){
            throw new GeneralException(ErrorStatus.STORE_NOT_AVAILABLE);
        }

        List<Menu> menus = menuRepositroy.findAllByRestaurantIdAndStatus(
                storeId,
                MenuStatus.ACTIVE
        );
        return MenuListRes.of(restaurant,menus);
    }
}
