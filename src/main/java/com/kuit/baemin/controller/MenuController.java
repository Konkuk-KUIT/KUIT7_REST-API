package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.CreateMenuReq;
import com.kuit.baemin.dto.response.MenuRes;
import com.kuit.baemin.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants/{restaurantId}/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    /** POST /restaurants/{restaurantId}/menus — 메뉴 등록 */
    @PostMapping
    public ApiResponse<Long> createMenu(
            @PathVariable Long restaurantId,
            @Valid @RequestBody CreateMenuReq req) {
        return ApiResponse.of(menuService.createMenu(restaurantId, req));
    }

    /** GET /restaurants/{restaurantId}/menus — 메뉴 목록 조회 */
    @GetMapping
    public ApiResponse<List<MenuRes>> getMenus(@PathVariable Long restaurantId) {
        return ApiResponse.of(menuService.getMenus(restaurantId));
    }
}