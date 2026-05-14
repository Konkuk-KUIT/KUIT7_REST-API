package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.response.MenuRes;
import com.kuit.baemin.dto.response.RestaurantRes;
import com.kuit.baemin.service.MenuService;
import com.kuit.baemin.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // 2. 추가
@RequestMapping("/restaurants") // 3. 공통 주소 설정
@RequiredArgsConstructor // 4. 추가
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final MenuService menuService;

    /**
     * GET /restaurants — 음식점 목록 조회
     */
    @GetMapping
    public ApiResponse<List<RestaurantRes>> getRestaurants(
            @RequestParam(required = false) String address,
            @RequestParam(required = false) Integer minOrderPrice
    ) {
        return ApiResponse.of(restaurantService.getRestaurants(address, minOrderPrice));
    }

    /**
     * GET /restaurants/{restaurantId}/menus — 음식점 메뉴 조회
     */
    @GetMapping("/{restaurantId}/menus")
    public ApiResponse<List<MenuRes>> getMenus(@PathVariable Long restaurantId, @RequestParam(required = false) String category) {
        return ApiResponse.of(menuService.getMenus(restaurantId, category));
    }
}
