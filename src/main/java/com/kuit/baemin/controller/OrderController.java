package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.CreateOrderReq;
import com.kuit.baemin.dto.response.CreateOrderRes;
import com.kuit.baemin.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/orders")
    public ApiResponse<CreateOrderRes> createOrder(
            @Valid @RequestBody CreateOrderReq req
    ) {
        return ApiResponse.of(orderService.createOrder(req));
    }
}
