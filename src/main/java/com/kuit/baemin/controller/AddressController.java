package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.CreateAddressReq;
import com.kuit.baemin.dto.request.UpdateAddressReq;
import com.kuit.baemin.dto.response.AddressListRes;
import com.kuit.baemin.dto.response.AddressRes;
import com.kuit.baemin.dto.response.DefaultAddressRes;
import com.kuit.baemin.dto.response.DeleteAddressRes;
import com.kuit.baemin.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping("/users/{userId}/addresses")
    public ApiResponse<AddressRes> createAddress(
            @PathVariable Long userId,
            @Valid @RequestBody CreateAddressReq req
            ){
        return ApiResponse.of(addressService.createAddress(userId, req));
    }
    @GetMapping("/users/{userId}/addresses")
    public ApiResponse<AddressListRes> getAddresses(
            @PathVariable Long userId
    ){
        return ApiResponse.of(addressService.getAddresses(userId));
    }
    @PatchMapping("/addresses/{addressId}")
    public ApiResponse<AddressRes>updateAddress(
            @PathVariable Long addressId,
            @Valid @RequestBody UpdateAddressReq req
            ){
        return ApiResponse.of(addressService.updateAddress(addressId, req));
    }
    @DeleteMapping("/addresses/{addressId}")
    public ApiResponse<DeleteAddressRes> deleteAddress(
            @PathVariable Long addressId
    ){
        return ApiResponse.of(addressService.deleteAddress(addressId));
    }
    @PatchMapping("/addresses/{addressId}/default")
    public ApiResponse<DefaultAddressRes> changeDefaultAddress(
            @PathVariable Long addressId
    ){
        return ApiResponse.of(addressService.changeDefaultAddress((addressId)));
    }
}
