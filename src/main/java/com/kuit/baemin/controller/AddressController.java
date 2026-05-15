package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.CreateAddressReq;
import com.kuit.baemin.dto.response.AddressRes;
import com.kuit.baemin.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members/{memberId}/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    /** POST /members/{memberId}/addresses — 주소 등록 */
    @PostMapping
    public ApiResponse<Long> createAddress(
            @PathVariable Long memberId,
            @Valid @RequestBody CreateAddressReq req) {
        return ApiResponse.of(addressService.createAddress(memberId, req));
    }

    /** GET /members/{memberId}/addresses — 주소 목록 조회 */
    @GetMapping
    public ApiResponse<List<AddressRes>> getAddresses(@PathVariable Long memberId) {
        return ApiResponse.of(addressService.getAddresses(memberId));
    }
}