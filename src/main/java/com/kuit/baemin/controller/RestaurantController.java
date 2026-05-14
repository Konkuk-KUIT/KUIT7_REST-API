package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.RestaurantCreateReq;
import com.kuit.baemin.dto.response.RestaurantRes;
import com.kuit.baemin.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    /**
     * 음식점 등록
     */
    @PostMapping
    public ApiResponse<Long> createRestaurant(
            @RequestBody RestaurantCreateReq req
    ) {

        return ApiResponse.of(
                restaurantService.createRestaurant(req)
        );
    }

    /**
     * 음식점 전체 조회
     */
    @GetMapping
    public ApiResponse<List<RestaurantRes>> getRestaurants() {

        return ApiResponse.of(
                restaurantService.getRestaurants()
        );
    }

    /**
     * 음식점 단건 조회
     */
    @GetMapping("/{restaurantId}")
    public ApiResponse<RestaurantRes> getRestaurant(
            @PathVariable Long restaurantId
    ) {

        return ApiResponse.of(
                restaurantService.getRestaurant(restaurantId)
        );
    }

    /**
     * 카테고리별 음식점 조회
     */
    @GetMapping("/categories/{categoryId}/restaurants")
    public ApiResponse<List<RestaurantRes>> getRestaurantsByCategory(
            @PathVariable Long categoryId
    ) {

        return ApiResponse.of(
                restaurantService.getRestaurantsByCategory(categoryId)
        );
    }

    /**
     * 배달비 기준 음식점 조회
     */
    @GetMapping("/delivery-fee")
    public ApiResponse<List<RestaurantRes>> getRestaurantsByDeliveryFee(
            @RequestParam BigDecimal fee
    ) {

        return ApiResponse.of(
                restaurantService.getRestaurantsByDeliveryFee(fee)
        );
    }

    /**
     * 음식점 삭제
     */
    @DeleteMapping("/{restaurantId}")
    public ApiResponse<Void> deleteRestaurant(
            @PathVariable Long restaurantId
    ) {

        restaurantService.deleteRestaurant(restaurantId);

        return ApiResponse.of(null);
    }
}
