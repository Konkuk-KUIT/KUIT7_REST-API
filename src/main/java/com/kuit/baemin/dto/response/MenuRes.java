package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.menu.Menu;
import com.kuit.baemin.domain.menu.MenuStatus;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MenuRes {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String imageUrl;
    private Boolean isSoldOut;
    private Integer displayOrder;
    private MenuStatus status;
    private Long restaurantId;
    private Long menuCategoryId;

    public static MenuRes from(Menu menu) {
        return MenuRes.builder()
                .id(menu.getId())
                .name(menu.getName())
                .description(menu.getDescription())
                .price(menu.getPrice())
                .imageUrl(menu.getImageUrl())
                .isSoldOut(menu.getIsSoldOut())
                .displayOrder(menu.getDisplayOrder())
                .status(menu.getStatus())
                .restaurantId(menu.getRestaurant().getId())
                .menuCategoryId(menu.getMenuCategory().getId())
                .build();
    }
}
