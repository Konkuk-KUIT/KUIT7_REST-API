package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.MenuCreateReq;
import com.kuit.baemin.dto.response.MenuRes;
import com.kuit.baemin.dto.response.PageRes;
import com.kuit.baemin.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/restaurants/{restaurantId}/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public ApiResponse<Long> create(@PathVariable Long restaurantId,
                                    @Valid @RequestBody MenuCreateReq req) {
        return ApiResponse.of(menuService.create(restaurantId, req));
    }

    @GetMapping
    public ApiResponse<PageRes<MenuRes>> list(
            @PathVariable Long restaurantId,
            @PageableDefault(size = 20, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ApiResponse.of(menuService.list(restaurantId, pageable));
    }
}
