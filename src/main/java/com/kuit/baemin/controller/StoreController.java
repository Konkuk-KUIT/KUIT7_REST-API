package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.response.MenuRes;
import com.kuit.baemin.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    // 4. 가게 목록 조회 (페이징 적용)
    @GetMapping
    public ApiResponse<Page> getStores(
            @RequestParam String category,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable) {
        return ApiResponse.onSuccess((Page) storeService.getStores(category, pageable));
    }

    // 5. 특정 가게 메뉴 조회
    @GetMapping("/{storeId}/menus")
    public ApiResponse<List<MenuRes>> getMenus(@PathVariable Long storeId) {
        return ApiResponse.onSuccess(storeService.getMenusByStore(storeId));
    }
}
