package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.menu.Menu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MenuRes {
    private Long menuId;
    private String category;
    private String name;
    private Integer price;
    private String menuPictureUrl;
    private Boolean popularity;

    public static MenuRes from(Menu menu) {
        return MenuRes.builder()
                .menuId(menu.getId())
                .category(menu.getCategory())
                .name(menu.getName())
                .price(menu.getPrice())
                .menuPictureUrl(menu.getMenuPictureUrl())
                .popularity(menu.getPopularity())
                .build();
    }
}