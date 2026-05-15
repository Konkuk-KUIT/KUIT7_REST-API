package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Store.Menu;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class MenuRes {

    private Long menuId;
    private String menuName;
    private BigDecimal menuPrice;

    public static MenuRes from(Menu menu) {
        return new MenuRes(
                menu.getMenuId(),
                menu.getMenuName(),
                menu.getMenuPrice()
        );
    }
}
