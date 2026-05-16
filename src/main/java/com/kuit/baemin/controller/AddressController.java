package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.AddressReq;
import com.kuit.baemin.dto.response.AddressRes;
import com.kuit.baemin.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {

  private final AddressService addressService;

  @GetMapping
  public ApiResponse<List<AddressRes>> getAddresses(@RequestParam Long memberId) {
    return ApiResponse.of(addressService.getAddresses(memberId));
  }

  @PostMapping
  public ApiResponse<Long> addAddress(
      @RequestParam Long memberId,
      @Valid @RequestBody AddressReq req) {
    return ApiResponse.of(addressService.addAddress(memberId, req));
  }

  @PatchMapping("/{addressId}")
  public ApiResponse<Void> updateAddress(
      @RequestParam Long memberId,
      @PathVariable Long addressId,
      @Valid @RequestBody AddressReq req) {
    addressService.updateAddress(memberId, addressId, req);
    return ApiResponse.of(null);
  }
}
