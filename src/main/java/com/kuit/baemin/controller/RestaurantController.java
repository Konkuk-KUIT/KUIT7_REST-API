package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.response.RestaurantListRes;
import com.kuit.baemin.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService RestaurantService;

    @GetMapping("/stores")
    public ApiResponse<RestaurantListRes> getStores(
            @PageableDefault(size=10) Pageable pageable
            ){
        return ApiResponse.of(RestaurantService.getStores(pageable));
    }
}
