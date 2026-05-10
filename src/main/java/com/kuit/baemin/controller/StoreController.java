package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.response.StoreRes;
import com.kuit.baemin.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stores")
@RequiredArgsConstructor
public class StoreController {

  private final StoreService storeService;

  @GetMapping
  public ApiResponse<List<StoreRes>> getStores() {
    return ApiResponse.of(storeService.getStores());
  }

  @GetMapping("/{storeId}")
  public ApiResponse<StoreRes> getStore(@PathVariable Long storeId) {
    return ApiResponse.of(storeService.getStore(storeId));
  }
}
