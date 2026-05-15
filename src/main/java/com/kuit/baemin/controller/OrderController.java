package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.OrderReq;
import com.kuit.baemin.dto.response.OrderListRes;
import com.kuit.baemin.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public ApiResponse<Long> createOrder(@Valid @RequestBody OrderReq req) {
        return ApiResponse.of(orderService.createOrder(req));
    }

    @GetMapping("/members/{memberId}/orders")
    public ApiResponse<OrderListRes> getOrders(@PathVariable Long memberId,
                                               @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ApiResponse.of(orderService.getOrders(memberId, pageable));
    }

    @DeleteMapping("/orders/{orderId}")
    public ApiResponse<Long> cancelOrder(@PathVariable Long orderId) {
        return ApiResponse.of(orderService.cancelOrder(orderId));
    }
}