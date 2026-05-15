package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.menu.Menu;
import com.kuit.baemin.domain.menu.MenuStatus;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MenuRes {

    private Long menuId;
    private String name;
    private Integer price;
    private String description;
    private Boolean isSoldOut;
    private MenuStatus status;

    public static MenuRes from(Menu menu){
        return MenuRes.builder()
                .menuId(menu.getId())
                .name(menu.getName())
                .price(menu.getPrice())
                .description(menu.getDescription())
                .isSoldOut(menu.getIsSoldOut())
                .status(menu.getStatus())
                .build();
    }
}
