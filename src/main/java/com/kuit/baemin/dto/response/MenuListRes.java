package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.menu.Menu;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MenuListRes {

    private Long storeId;
    private String name;
    private List<MenuRes> menus;

    public static MenuListRes of(Restaurant restaurant, List<Menu> menus){
        return MenuListRes.builder()
                .storeId(restaurant.getId())
                .name(restaurant.getName())
                .menus(
                        menus.stream()
                                .map(MenuRes::from)
                                .toList()
                )
                .build();
    }
}
