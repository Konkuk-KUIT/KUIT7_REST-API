package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.OrderCreateReq;
import com.kuit.baemin.dto.response.OrderRes;
import com.kuit.baemin.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * POST /orders — 주문 생성
     */
    @PostMapping("/orders")
    public ApiResponse<Long> createOrder(@Valid @RequestBody OrderCreateReq req) {
        return ApiResponse.of(orderService.createOrder(req));
    }

    /**
     * GET /members/{memberId}/orders — 회원 주문 내역 조회
     */
    @GetMapping("/members/{memberId}/orders")
    public ApiResponse<Page<OrderRes>> getMemberOrders(
            @PathVariable("memberId") Long memberId,
            Pageable pageable
    ) {
        return ApiResponse.of(orderService.getMemberOrders(memberId, pageable));
    }
}
