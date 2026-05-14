package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.AddressReq;
import com.kuit.baemin.dto.response.AddressRes;
import com.kuit.baemin.dto.response.CouponRes;
import com.kuit.baemin.dto.response.MemberRes;
import com.kuit.baemin.service.AddressService;
import com.kuit.baemin.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    /**
     * POST /addresses — 주소 기록 및 반영
     * 야매 인증을 위해 ?memberId=1 형태로 회원 ID를 같이 받습니다.
     */
    @PostMapping
    public ApiResponse<Long> createAddress(
            @RequestParam Long memberId, // 로그인 대신 파라미터로 대충 회원 ID 받아버리기
            @Valid @RequestBody AddressReq request
    ) {
        return ApiResponse.of(addressService.createAddress(memberId, request));
    }

    /**
     * GET /addresses/{memberId} — 사용자의 주소 목록 조회 (카테고리 필터링 포함)
     */
    @GetMapping("/{memberId}")
    public ApiResponse<AddressRes> getMyCoupons(
            @RequestParam Long memberId,
            @RequestHeader(value = "X-Login-Id", required = false) Long loginMemberId
    ) {
        return ApiResponse.of(addressService.getAddress(memberId, loginMemberId));
    }
}
