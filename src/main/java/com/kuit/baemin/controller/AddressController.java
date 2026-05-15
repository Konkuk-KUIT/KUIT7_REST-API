package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.AddressCreateReq;
import com.kuit.baemin.dto.response.AddressRes;
import com.kuit.baemin.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ApiResponse<AddressRes> createAddress(@Valid @RequestBody AddressCreateReq req) {
        return ApiResponse.of(addressService.createAddress(req));
    }
}
