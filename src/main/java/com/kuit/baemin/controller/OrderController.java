package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.CreateOrderReq;
import com.kuit.baemin.dto.response.OrderRes;
import com.kuit.baemin.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Order", description = "주문 관련 API")
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "주문 생성")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "주문 성공")
    @PostMapping
    public ApiResponse<OrderRes> createOrder(@Valid @RequestBody CreateOrderReq req) {
        return ApiResponse.of(orderService.createOrder(req));
    }
}