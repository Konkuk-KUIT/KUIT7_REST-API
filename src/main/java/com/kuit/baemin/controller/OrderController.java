// OrderController.java
package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.CreateOrderReq;
import com.kuit.baemin.dto.response.OrderRes;
import com.kuit.baemin.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /** POST /orders — 주문 생성 */
    @PostMapping
    public ApiResponse<OrderRes> createOrder(@Valid @RequestBody CreateOrderReq req) {
        return ApiResponse.of(orderService.createOrder(req));
    }
}