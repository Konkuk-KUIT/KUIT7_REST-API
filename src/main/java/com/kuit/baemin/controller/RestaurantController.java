package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.RegisterRestaurantReq;
import com.kuit.baemin.dto.request.UpdateRestaurantReq;
import com.kuit.baemin.dto.response.RestaurantRes;
import com.kuit.baemin.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;
    /**
     * POST /stores - 신규 가게 등록
     */
    @PostMapping
    public ApiResponse<Long> registerRestaurant(@Valid @RequestBody RegisterRestaurantReq req) {
        return ApiResponse.of(restaurantService.registerRestaurant(req));
    }

    /**
     * GET /stores - 가게 전체 조회
     */
    @GetMapping
    public ApiResponse<List<RestaurantRes>> getAllRestaurants() {
        return ApiResponse.of(restaurantService.getAllRestaurants());
    }

    /**
     * GET /stores/{restaurantId} - 가게 단일 조회
     */
    @GetMapping("/{restaurantId}")
    public ApiResponse<RestaurantRes> getRestaurant(@PathVariable Long restaurantId) {
        return ApiResponse.of(restaurantService.getRestaurant(restaurantId));
    }

    /**
     * PATCH /stores/{restaurantId} - 가게 정보 수정
     */
    @PatchMapping("/{restaurantId}")
    public ApiResponse<Long> updateRestaurant(@PathVariable Long restaurantId, @Valid @RequestBody UpdateRestaurantReq req) {
        return ApiResponse.of(restaurantService.updateRestaurant(restaurantId, req));
    }

    /**
     * DELETE /members/{restaurantId} - 가게 삭제
     */
    @DeleteMapping("/{restaurantId}")
    public ApiResponse<Long> deleteRestaurant(@PathVariable Long restaurantId) {
        return ApiResponse.of(restaurantService.deleteRestaurant(restaurantId));
    }
}
