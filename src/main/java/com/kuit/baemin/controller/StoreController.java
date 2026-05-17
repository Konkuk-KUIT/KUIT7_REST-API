package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.response.StoreListRes;
import com.kuit.baemin.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {

    private final StoreService storeService;
    private final com.kuit.baemin.service.MenuService menuService;

    /**
     * GET /stores — 가게 목록 조회
     */
    @GetMapping
    public ApiResponse<Page<StoreListRes>> getStoreList(
            @RequestParam String category,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {
        return ApiResponse.of(storeService.getStoreList(category, pageable));
    }

    /**
     * GET /stores/{storeId}/menus — 특정 가게 메뉴 목록 조회
     */
    @GetMapping("/{storeId}/menus")
    public ApiResponse<java.util.List<com.kuit.baemin.dto.response.MenuRes>> getMenusByStore(@PathVariable Long storeId) {
        return ApiResponse.of(menuService.getMenusByStore(storeId));
    }
}