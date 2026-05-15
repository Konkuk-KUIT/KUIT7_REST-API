package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.OrderReq;
import com.kuit.baemin.dto.response.OrderRes;
import com.kuit.baemin.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // API 10: 주문 생성
    @PostMapping
    public ResponseEntity<ApiResponse<Long>> createOrder(
            @RequestHeader("X-Member-Id") Long memberId,
            @Valid @RequestBody OrderReq req) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.onSuccess(orderService.createOrder(memberId, req)));
    }

    // 주문 단건 조회
    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<OrderRes>> getOrder(
            @PathVariable Long orderId) {
        return ResponseEntity.ok(ApiResponse.onSuccess(orderService.getOrder(orderId)));
    }
}