package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.OrderCreateReq;
import com.kuit.baemin.dto.response.OrderRes;
import com.kuit.baemin.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    // 9. 주문하기
    @PostMapping("/orders")
    public ApiResponse<Long> createOrder(@Valid @RequestBody OrderCreateReq req) {
        return ApiResponse.onSuccess(orderService.createOrder(req));
    }

    // 10. 사용자의 주문 내역 조회 (페이징 + Fetch Join 권장)
    @GetMapping("/users/{userId}/orders")
    public ApiResponse<Slice<OrderRes>> getUserOrders(
            @PathVariable Long userId,
            @PageableDefault(size = 10) Pageable pageable) {
        return ApiResponse.onSuccess(orderService.getUserOrderHistory(userId, pageable));
    }
}
