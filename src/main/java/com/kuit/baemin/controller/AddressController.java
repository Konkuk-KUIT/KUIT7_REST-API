package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.CreateAddressReq;
import com.kuit.baemin.dto.response.AddressRes;
import com.kuit.baemin.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Address", description = "주소 관련 API")
@RestController
@RequestMapping("/members/{memberId}/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @Operation(summary = "주소 등록")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "등록 성공")
    @PostMapping
    public ApiResponse<Long> createAddress(
            @Parameter(description = "회원 ID") @PathVariable Long memberId,
            @Valid @RequestBody CreateAddressReq req) {
        return ApiResponse.of(addressService.createAddress(memberId, req));
    }

    @Operation(summary = "주소 목록 조회")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping
    public ApiResponse<List<AddressRes>> getAddresses(
            @Parameter(description = "회원 ID") @PathVariable Long memberId) {
        return ApiResponse.of(addressService.getAddresses(memberId));
    }
}