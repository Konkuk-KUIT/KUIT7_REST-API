package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Restaurant.Menu;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MenuRes {
    private Long id;
    private String name;
    private Long price;

    public static MenuRes from(Menu menu) {
        return MenuRes.builder()
                .id(menu.getId())
                .name(menu.getName())
                .price(menu.getPrice())
                .build();
    }
}