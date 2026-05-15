package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Menu.Menu;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MenuRes {
    private Long menuId;
    private String category;
    private String menuName;
    private Integer price; // ERD에 bigint로 되어있다면 Long도 무방합니다.

    // Entity -> DTO 변환 메서드
    public static MenuRes from(Menu menu) {
        return MenuRes.builder()
                .menuId(menu.getId())
                .category(menu.getCategory())
                .menuName(menu.getMenuName())
                .price(menu.getPrice())
                .build();
    }
}