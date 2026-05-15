package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.MenuUpdateReq;
import com.kuit.baemin.dto.response.MenuRes;
import com.kuit.baemin.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PatchMapping("/{menuId}")
    public ApiResponse<MenuRes> updateMenu(@PathVariable Long menuId, @Valid @RequestBody MenuUpdateReq req) {
        return ApiResponse.of(menuService.updateMenu(menuId, req));
    }
}
