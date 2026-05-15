package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.menu.Menu;
import lombok.Builder;
import lombok.Getter;

//메뉴 목록 조회용 - 옵션 포함 x
@Getter
@Builder
public class MenuRes {

    private Long id;
    private String name;
    private Integer price;

    public static MenuRes from(Menu menu) {
        return MenuRes.builder()
                .id(menu.getId())
                .name(menu.getName())
                .price(menu.getPrice())
                .build();
    }
}