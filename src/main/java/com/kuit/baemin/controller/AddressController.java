package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.AddressDelReq;
import com.kuit.baemin.dto.request.AddressReq;
import com.kuit.baemin.dto.request.OrderCancelReq;
import com.kuit.baemin.dto.response.AddressDelRes;
import com.kuit.baemin.dto.response.AddressRes;
import com.kuit.baemin.dto.response.CouponRes;
import com.kuit.baemin.dto.response.MemberRes;
import com.kuit.baemin.service.AddressService;
import com.kuit.baemin.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
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
     * 요구하신 대로 생성된 ID(Long)를 직접 반환합니다.
     */
    @PostMapping
    public ApiResponse<Long> createAddress(
            @Valid @RequestBody AddressReq request
    ) {
        // 1. 서비스에서 저장 후 생성된 PK(ID)를 받아옵니다.
        Long savedAddressId = addressService.createAddress(request.getMemberId(), request.getName(), request.getCategory());

        // 2. 리뷰 작성 API와 동일하게 Long 값을 그대로 응답 객체에 담습니다.
        return ApiResponse.of(savedAddressId);
    }

    /**
     * DELETE /addresses/{memberId}/{addressId} — 주소 삭제
     */
    @DeleteMapping("/{memberId}/{addressId}")
    public ApiResponse<AddressDelRes> deleteAddress(
            @PathVariable Long memberId,
            @Valid @RequestBody AddressDelReq request,
            @PathVariable Long addressId
    ) {
        return ApiResponse.of(addressService.deleteAddress(memberId, request.getLoginMemberId(), addressId));
    }

    /**
     * GET /addresses/{memberId} — 사용자의 주소 목록 조회 (카테고리 필터링 포함)
     */
    @GetMapping("/{memberId}")
    public ApiResponse<AddressRes> getMyCoupons(
            @PathVariable Long memberId,
            @RequestParam(value = "loginMemberId", required = false) Long loginMemberId
    ) {
        return ApiResponse.of(addressService.getAddress(memberId, loginMemberId));
    }
}
