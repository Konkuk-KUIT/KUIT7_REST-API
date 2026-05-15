package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.response.RestaurantRes;
import com.kuit.baemin.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    /**
     * GET /restaurants — 음식점 목록 조회
     */
    @GetMapping
    public ApiResponse<Page<RestaurantRes>> getRestaurants(
            @RequestParam(required = false) String category,
            Pageable pageable
    ) {
        return ApiResponse.of(restaurantService.getRestaurants(category, pageable));
    }

    /**
     * GET /restaurants/{restaurantId} — 음식점 단건 조회
     */
    @GetMapping("/{restaurantId}")
    public ApiResponse<RestaurantRes> getRestaurant(@PathVariable("restaurantId") Long restaurantId) {
        return ApiResponse.of(restaurantService.getRestaurant(restaurantId));
    }
}
