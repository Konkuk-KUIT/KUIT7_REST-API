package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.response.CouponRes;
import com.kuit.baemin.service.AddressService;
import com.kuit.baemin.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    /**
     * GET /coupons/{memberId} — 사용자의 쿠폰 목록 조회 (카테고리 필터링 포함)
     */
    @GetMapping("/{memberId}")
    public ApiResponse<List<CouponRes>> getMyCoupons(
            @RequestParam Long memberId,
            @RequestHeader(value = "X-Login-Id", required = false) Long loginMemberId,
            @RequestParam(required = false) String category
    ) {
        return ApiResponse.of(couponService.getMyCoupons(memberId, loginMemberId, category));
    }
}
