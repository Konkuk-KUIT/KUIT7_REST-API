package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.menu.Menu;
import com.kuit.baemin.domain.menu.MenuStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MenuRes {

    private Long id;
    private String name;
    private String description;
    private Integer price;
    private MenuStatus status;

    public static MenuRes from(Menu menu) {
        return MenuRes.builder()
                .id(menu.getId())
                .name(menu.getName())
                .description(menu.getDescription())
                .price(menu.getPrice())
                .status(menu.getStatus())
                .build();
    }
}
