package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.response.MenuRes;
import com.kuit.baemin.dto.response.RestaurantRes;
import com.kuit.baemin.service.MenuService;
import com.kuit.baemin.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final MenuService menuService;

    /**
     * GET /restaurants - 식당 목록 조회
     */
    @GetMapping
    @Operation(summary = "식당 목록 조회")
    public ApiResponse<List<RestaurantRes>> findAll(@PageableDefault(size = 10) Pageable pageable) {
        return ApiResponse.onSuccess(restaurantService.findAll(pageable));
    }

    /**
     * GET /restaurants/{id}/menus - 식당 메뉴 목록 조회
     */
    @GetMapping("/{restaurantId}/menus")
    @Operation(summary = "식당 메뉴 목록 조회")
    public ApiResponse<List<MenuRes>> getMenus(@PathVariable Long restaurantId,
                                               @PageableDefault(size = 10) Pageable pageable) {
        return ApiResponse.onSuccess(menuService.findByRestaurantId(restaurantId, pageable));
    }
}
