package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.MenuReq;
import com.kuit.baemin.dto.response.MenuRes;
import com.kuit.baemin.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants/{restaurantId}/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    // API 8: 메뉴 추가
    @PostMapping
    public ResponseEntity<ApiResponse<Long>> createMenu(
            @PathVariable Long restaurantId,
            @Valid @RequestBody MenuReq req) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.onSuccess(menuService.createMenu(restaurantId, req)));
    }

    // API 9: 메뉴 목록 조회
    @GetMapping
    public ResponseEntity<ApiResponse<Page<MenuRes>>> getMenus(
            @PathVariable Long restaurantId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(ApiResponse.onSuccess(menuService.getMenus(restaurantId, pageable)));
    }
}