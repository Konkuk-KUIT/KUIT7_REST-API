package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.response.MenuListRes;
import com.kuit.baemin.dto.response.StoreDetailRes;
import com.kuit.baemin.dto.response.StoreListRes;
import com.kuit.baemin.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
@Validated
public class StoreController {

    private final StoreService storeService;

    @Operation(summary = "가게 조회", description = "가게를 조회한다")
    @GetMapping
    public ApiResponse<StoreListRes> getStores(
            @RequestParam(defaultValue = "0") @PositiveOrZero int page,
            @RequestParam(defaultValue = "10") @Positive int size
    ) {
        return ApiResponse.of(storeService.getStores(page, size));
    }

    @Operation(summary = "특정 가게 조회", description = "특정 가게를 조회한다")
    @GetMapping("/{storeId}")
    public ApiResponse<StoreDetailRes> getStore(@PathVariable @Positive Long storeId) {
        return ApiResponse.of(storeService.getStore(storeId));
    }

    @Operation(summary = "가게 메뉴 조회", description = "특정 가게의 메뉴 목록을 조회한다. 페이징을 적용한다.")
    @GetMapping("/{storeId}/menus")
    public ApiResponse<MenuListRes> getMenus(
            @PathVariable @Positive Long storeId,
            @RequestParam(defaultValue = "0") @PositiveOrZero int page,
            @RequestParam(defaultValue = "10") @Positive int size
    ) {
        return ApiResponse.of(storeService.getMenus(storeId, page, size));
    }
}
