package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.CreateRestaurantReq;
import com.kuit.baemin.dto.response.RestaurantRes;
import com.kuit.baemin.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    /** POST /restaurants — 음식점 등록 */
    @PostMapping
    public ApiResponse<Long> createRestaurant(@Valid @RequestBody CreateRestaurantReq req) {
        return ApiResponse.of(restaurantService.createRestaurant(req));
    }

    /** GET /restaurants — 음식점 목록 조회 (페이징) */
    @GetMapping
    public ApiResponse<Page<RestaurantRes>> getRestaurants(
            @PageableDefault(size = 10) Pageable pageable) {
        return ApiResponse.of(restaurantService.getRestaurants(pageable));
    }

    /** GET /restaurants/{restaurantId} — 음식점 단건 조회 */
    @GetMapping("/{restaurantId}")
    public ApiResponse<RestaurantRes> getRestaurant(@PathVariable Long restaurantId) {
        return ApiResponse.of(restaurantService.getRestaurant(restaurantId));
    }
}