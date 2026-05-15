package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.response.MenuListRes;
import com.kuit.baemin.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @GetMapping("/stores/{storeId}/menus")
    public ApiResponse<MenuListRes> getMenus(
            @PathVariable Long storeId
    ){
        return ApiResponse.of(menuService.getMenus(storeId));
    }
}
