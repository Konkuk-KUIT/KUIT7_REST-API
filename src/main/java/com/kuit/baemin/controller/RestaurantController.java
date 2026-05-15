package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.RestaurantReq;
import com.kuit.baemin.dto.response.RestaurantRes;
import com.kuit.baemin.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    // API 4: 식당 생성
    @PostMapping
    public ResponseEntity<ApiResponse<Long>> createRestaurant(
            @Valid @RequestBody RestaurantReq req) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.onSuccess(restaurantService.createRestaurant(req)));
    }

    // API 5: 식당 목록 조회
    @GetMapping
    public ResponseEntity<ApiResponse<Page<RestaurantRes>>> getRestaurants(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(ApiResponse.onSuccess(restaurantService.getRestaurants(pageable)));
    }

    // API 6: 식당 상세 조회
    @GetMapping("/{restaurantId}")
    public ResponseEntity<ApiResponse<RestaurantRes>> getRestaurant(
            @PathVariable Long restaurantId) {
        return ResponseEntity.ok(ApiResponse.onSuccess(restaurantService.getRestaurant(restaurantId)));
    }

    // API 7: 식당 삭제
    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<ApiResponse<Void>> deleteRestaurant(
            @PathVariable Long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
        return ResponseEntity.ok(ApiResponse.onSuccess(null));
    }

    // API 11: 식당 정보 수정
    @PatchMapping("/{restaurantId}")
    public ResponseEntity<ApiResponse<RestaurantRes>> updateRestaurant(
            @PathVariable Long restaurantId,
            @Valid @RequestBody RestaurantReq req) {
        return ResponseEntity.ok(ApiResponse.onSuccess(restaurantService.updateRestaurant(restaurantId, req)));
    }
}