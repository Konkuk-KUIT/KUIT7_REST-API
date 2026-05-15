package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.menu.Menu;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MenuRes {
    private Long id;
    private String menuName;
    private String description;
    private Integer price;
    private Boolean isAvailable;

    public static MenuRes from(Menu menu) {
        return MenuRes.builder()
                .id(menu.getId())
                .menuName(menu.getMenuName())
                .description(menu.getDescription())
                .price(menu.getPrice())
                .isAvailable(menu.getIsAvailable())
                .build();
    }
}