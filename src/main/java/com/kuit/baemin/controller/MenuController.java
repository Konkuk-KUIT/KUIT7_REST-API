package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.MenuReq;
import com.kuit.baemin.dto.response.MenuRes;
import com.kuit.baemin.service.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "메뉴(Menu)", description = "메뉴 정보 관련 API")
@RestController
@RequestMapping("/restaurants/{restaurantId}/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    /**
     * API 6: POST /restaurants/{restaurantId}/menus — 메뉴 추가
     */
    @Operation(summary = "메뉴 추가", description = "특정 식당에 새로운 메뉴를 추가합니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "메뉴 생성 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "식당을 찾을 수 없음"),
    })
    @PostMapping
    public ResponseEntity<ApiResponse<Long>> createMenu(
            @Parameter(description = "식당 ID", example = "1")
            @PathVariable Long restaurantId,
            @Valid @RequestBody MenuReq req) {

        Long menuId = menuService.createMenu(restaurantId, req);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.onSuccess(menuId));
    }

    /**
     * API 7: GET /restaurants/{restaurantId}/menus — 식당 메뉴 목록 조회
     */
    @Operation(summary = "식당 메뉴 목록 조회", description = "특정 식당의 메뉴 목록을 페이징으로 조회합니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "식당을 찾을 수 없음"),
    })
    @GetMapping
    public ResponseEntity<ApiResponse<Page<MenuRes>>> getMenusByRestaurant(
            @Parameter(description = "식당 ID", example = "1")
            @PathVariable Long restaurantId,
            @Parameter(description = "페이지 번호 (0부터 시작)", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 크기", example = "20")
            @RequestParam(defaultValue = "20") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<MenuRes> menus = menuService.getMenusByRestaurant(restaurantId, pageable);
        return ResponseEntity.ok(ApiResponse.onSuccess(menus));
    }
}
