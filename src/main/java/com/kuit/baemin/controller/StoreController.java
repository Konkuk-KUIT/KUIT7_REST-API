package com.kuit.baemin.controller;


import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.ReviewReq;
import com.kuit.baemin.dto.response.CreateReviewRes;
import com.kuit.baemin.dto.response.MenuRes;
import com.kuit.baemin.dto.response.StoreRes;
import com.kuit.baemin.service.MenuService;
import com.kuit.baemin.service.OrderService;
import com.kuit.baemin.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;
    private final MenuService menuService;
    private final OrderService orderService;


    // 특정 가게 정보 조회
    @GetMapping("/{store_id}")
    public ApiResponse<StoreRes> getStore(@PathVariable("store_id") Long storeId){
        return ApiResponse.of(storeService.getStore(storeId));
    }


    // 특정 가게의 메뉴 목록 조회
    @GetMapping("/{store_id}/menus")
    public ApiResponse<List<MenuRes>> getStoreMenus(@PathVariable("store_id") Long storeId) {
        return ApiResponse.of(menuService.getStoreMenus(storeId));
    }

    @PostMapping("/{store_id}/review")
    public ApiResponse<CreateReviewRes> createReview(@PathVariable("store_id") Long storeId,
                                                     @Valid @RequestBody ReviewReq req){
        return ApiResponse.of(storeService.createReview(storeId, req));
    }



}
