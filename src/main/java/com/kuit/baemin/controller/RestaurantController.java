package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.RestaurantCreateReq;
import com.kuit.baemin.dto.response.PageRes;
import com.kuit.baemin.dto.response.RestaurantDetailRes;
import com.kuit.baemin.dto.response.RestaurantRes;
import com.kuit.baemin.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public ApiResponse<Long> create(@Valid @RequestBody RestaurantCreateReq req) {
        return ApiResponse.of(restaurantService.create(req));
    }

    @GetMapping
    public ApiResponse<PageRes<RestaurantRes>> list(
            @RequestParam(required = false) Long categoryId,
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ApiResponse.of(restaurantService.list(categoryId, pageable));
    }

    @GetMapping("/{restaurantId}")
    public ApiResponse<RestaurantDetailRes> getDetail(@PathVariable Long restaurantId) {
        return ApiResponse.of(restaurantService.getDetail(restaurantId));
    }
}
