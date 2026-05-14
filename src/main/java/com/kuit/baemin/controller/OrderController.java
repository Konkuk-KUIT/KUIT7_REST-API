package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.OrderCancelReq;
import com.kuit.baemin.dto.request.OrderReq;
import com.kuit.baemin.dto.response.OrderCancelRes;
import com.kuit.baemin.dto.response.OrderRes;
import com.kuit.baemin.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    /**
     * POST /orders — 주문 진행
     */
    @PostMapping
    public ApiResponse<Long> createOrder(
            @Valid @RequestBody OrderReq req
    ) {
        return ApiResponse.of(orderService.createOrder(req));
    }

    /**
     * PATCH /orders/{orderId}/cancel — 주문 취소 반영
     */
    @PatchMapping("/{orderId}/cancel")
    public ApiResponse<OrderCancelRes> cancelOrder(
            @PathVariable("orderId") Long orderId,
            @Valid @RequestBody OrderCancelReq request
    ) {
        OrderCancelRes response = orderService.cancelOrder(orderId, request.getMemberId());
        return ApiResponse.of(response);
    }

    /**
     * GET /orders/{orderId} — 회원 단건 조회
     */
    @GetMapping("/{orderId}")
    public ApiResponse<OrderRes> getOrder(@PathVariable Long orderId) {
        return ApiResponse.of(orderService.getOrder(orderId));
    }
}
