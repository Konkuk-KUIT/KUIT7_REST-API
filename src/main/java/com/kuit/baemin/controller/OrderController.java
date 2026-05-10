package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.OrderReq;
import com.kuit.baemin.dto.response.OrderRes;
import com.kuit.baemin.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @GetMapping
  public ApiResponse<List<OrderRes>> getOrders(@RequestParam Long memberId) {
    return ApiResponse.of(orderService.getOrders(memberId));
  }

  @PostMapping
  public ApiResponse<Long> createOrder(
      @RequestParam Long memberId,
      @Valid @RequestBody OrderReq req) {
    return ApiResponse.of(orderService.createOrder(memberId, req));
  }

  @GetMapping("/{orderId}")
  public ApiResponse<OrderRes> getOrder(
      @RequestParam Long memberId,
      @PathVariable Long orderId) {
    return ApiResponse.of(orderService.getOrder(memberId, orderId));
  }
}
