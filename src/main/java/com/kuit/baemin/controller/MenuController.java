package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.CreateMenuReq;
import com.kuit.baemin.dto.response.MenuRes;
import com.kuit.baemin.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Menu", description = "메뉴 관련 API")
@RestController
@RequestMapping("/restaurants/{restaurantId}/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @Operation(summary = "메뉴 등록")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "등록 성공")
    @PostMapping
    public ApiResponse<Long> createMenu(
            @Parameter(description = "음식점 ID") @PathVariable Long restaurantId,
            @Valid @RequestBody CreateMenuReq req) {
        return ApiResponse.of(menuService.createMenu(restaurantId, req));
    }

    @Operation(summary = "메뉴 목록 조회")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping
    public ApiResponse<List<MenuRes>> getMenus(
            @Parameter(description = "음식점 ID") @PathVariable Long restaurantId) {
        return ApiResponse.of(menuService.getMenus(restaurantId));
    }
}