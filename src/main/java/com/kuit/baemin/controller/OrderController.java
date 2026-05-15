package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.OrderCreateReq;
import com.kuit.baemin.dto.response.OrderCreateRes;
import com.kuit.baemin.dto.response.OrderDetailRes;
import com.kuit.baemin.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ApiResponse<OrderCreateRes> createOrder(@Valid @RequestBody OrderCreateReq req) {
        return ApiResponse.of(orderService.createOrder(req));
    }

    @GetMapping("/{orderId}")
    public ApiResponse<OrderDetailRes> getOrder(@PathVariable Long orderId) {
        return ApiResponse.of(orderService.getOrder(orderId));
    }
}
