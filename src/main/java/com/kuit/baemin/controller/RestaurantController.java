package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.response.MenuRes;
import com.kuit.baemin.dto.response.RestaurantDetailRes;
import com.kuit.baemin.dto.response.RestaurantListRes;
import com.kuit.baemin.service.RestaurantService;
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

    /**
     * GET /restaurants?page=0&size=10
     * @PageableDefault: 파라미터가 없을 때 기본으로 0페이지, 10개씩 가져오도록 설정
     */
    @GetMapping
    public ApiResponse<RestaurantListRes> getRestaurants(
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ApiResponse.of(restaurantService.getRestaurants(pageable));
    }

    /**
     * GET /restaurants/{restaurantId}
     */
    @GetMapping("/{restaurantId}")
    public ApiResponse<RestaurantDetailRes> getRestaurant(@PathVariable Long restaurantId) {
        return ApiResponse.of(restaurantService.getRestaurant(restaurantId));
    }

    /**
     * GET /restaurants/{restaurantId}/menus
     */
    @GetMapping("/{restaurantId}/menus")
    public ApiResponse<List<MenuRes>> getMenus(@PathVariable Long restaurantId) {
        return ApiResponse.of(restaurantService.getMenus(restaurantId));
    }
}