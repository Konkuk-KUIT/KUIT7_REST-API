package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.CreateRestaurantReq;
import com.kuit.baemin.dto.response.RestaurantRes;
import com.kuit.baemin.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Restaurant", description = "음식점 관련 API")
@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Operation(summary = "음식점 등록")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "등록 성공")
    @PostMapping
    public ApiResponse<Long> createRestaurant(@Valid @RequestBody CreateRestaurantReq req) {
        return ApiResponse.of(restaurantService.createRestaurant(req));
    }

    @Operation(summary = "음식점 목록 조회 (페이징)")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping
    public ApiResponse<Page<RestaurantRes>> getRestaurants(
            @PageableDefault(size = 10) Pageable pageable) {
        return ApiResponse.of(restaurantService.getRestaurants(pageable));
    }

    @Operation(summary = "음식점 단건 조회")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping("/{restaurantId}")
    public ApiResponse<RestaurantRes> getRestaurant(
            @Parameter(description = "음식점 ID") @PathVariable Long restaurantId) {
        return ApiResponse.of(restaurantService.getRestaurant(restaurantId));
    }
}