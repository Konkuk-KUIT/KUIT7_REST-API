package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Menu.Menu;
import com.kuit.baemin.domain.Menu.MenuStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class MenuRes {

    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String image;

    // private Restaurant restaurant;
    // 식당 ID는 이미 특정 식당에 대한 메뉴를 조회하는 것이기 때문에 필요하지 않음

    public static MenuRes from(Menu menu) {

        return MenuRes.builder()
                .id(menu.getId())
                .name(menu.getName())
                .price(menu.getPrice())
                .description(menu.getDescription())
                .image(menu.getImage())
                .build();
    }
}
