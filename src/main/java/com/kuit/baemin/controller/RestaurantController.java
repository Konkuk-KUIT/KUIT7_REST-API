package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.RestaurantCreateReq;
import com.kuit.baemin.dto.response.RestaurantRes;
import com.kuit.baemin.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public ApiResponse<RestaurantRes> createRestaurant(@Valid @RequestBody RestaurantCreateReq req) {
        return ApiResponse.of(restaurantService.createRestaurant(req));
    }

    @DeleteMapping("/{restaurantId}")
    public ApiResponse<Long> deleteRestaurant(@PathVariable Long restaurantId) {
        return ApiResponse.of(restaurantService.deleteRestaurant(restaurantId));
    }
}
