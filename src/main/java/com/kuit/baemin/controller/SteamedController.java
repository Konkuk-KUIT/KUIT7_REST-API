package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.response.SliceRes;
import com.kuit.baemin.dto.response.SteamedRes;
import com.kuit.baemin.service.SteamedService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members/{memberId}/steamed")
@RequiredArgsConstructor
public class SteamedController {

    private final SteamedService steamedService;

    /**
     * GET /members/{memberId}/steamed - 찜 목록 조회
     */
    @GetMapping
    public ApiResponse<SliceRes<SteamedRes>> getSteamedList(
            @PathVariable Long memberId,
            Pageable pageable
    ) {
        return ApiResponse.of(steamedService.getAllSteamed(memberId, pageable));
    }

    /**
     * POST /members/{memberId}/steamed/{restaurantId} - 가게 찜하기
     */
    @PostMapping("/{restaurantId}")
    public ApiResponse<Long> steamRestaurant(
            @PathVariable Long memberId,
            @PathVariable Long restaurantId
    ) {
        return ApiResponse.of(steamedService.steamRestaurant(memberId, restaurantId));
    }

    /**
     * DELETE /members/{memberId}/steamed/{restaurantId} - 찜 삭제하기
     */
    @DeleteMapping("/{restaurantId}")
    public ApiResponse<Long> unsteamRestaurant(
            @PathVariable Long memberId,
            @PathVariable Long restaurantId
    ) {
        return ApiResponse.of(steamedService.unsteamrestaurant(memberId, restaurantId));
    }
}
