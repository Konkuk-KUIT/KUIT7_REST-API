package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.response.MenuDetailRes;
import com.kuit.baemin.dto.response.MenuRes;
import com.kuit.baemin.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    /**
     * GET /restaurants/{restaurantId}/menus — 특정 음식점 메뉴 목록 조회
     */
    @GetMapping("/restaurants/{restaurantId}/menus")
    public ApiResponse<List<MenuRes>> getRestaurantMenus(
            @PathVariable("restaurantId") Long restaurantId
    ) {
        return ApiResponse.of(menuService.getRestaurantMenus(restaurantId));
    }

    /**
     * GET /menus/{menuId} — 메뉴 상세 조회 (옵션 포함)
     */
    @GetMapping("/menus/{menuId}")
    public ApiResponse<MenuDetailRes> getMenu(
            @PathVariable("menuId") Long menuId
    ) {
        return ApiResponse.of(menuService.getMenu(menuId));
    }
}