package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.AddressCreateReq;
import com.kuit.baemin.dto.response.AddressRes;
import com.kuit.baemin.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    /**
     * POST /addresses — 주소 등록
     */
    @PostMapping("/addresses")
    public ApiResponse<Long> createAddress(@Valid @RequestBody AddressCreateReq req) {
        return ApiResponse.of(addressService.createAddress(req));
    }

    /**
     * GET /members/{memberId}/addresses — 회원 주소 목록 조회
     */
    @GetMapping("/members/{memberId}/addresses")
    public ApiResponse<List<AddressRes>> getMemberAddresses(
            @PathVariable("memberId") Long memberId
    ) {
        return ApiResponse.of(addressService.getMemberAddresses(memberId));
    }
}
