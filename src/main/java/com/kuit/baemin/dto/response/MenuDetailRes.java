package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.menu.Menu;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

//메뉴 상세 조회용 - 옵션 포함
@Getter
@Builder
public class MenuDetailRes {

    private Long id;
    private String name;
    private Integer price;
    private List<MenuOptionRes> options;

    public static MenuDetailRes of(Menu menu, List<MenuOptionRes> options) {
        return MenuDetailRes.builder()
                .id(menu.getId())
                .name(menu.getName())
                .price(menu.getPrice())
                .options(options)
                .build();
    }
}
