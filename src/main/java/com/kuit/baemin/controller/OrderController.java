package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.OrderCreateReq;
import com.kuit.baemin.dto.response.OrderRes;
import com.kuit.baemin.dto.response.PageRes;
import com.kuit.baemin.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public ApiResponse<Long> create(@Valid @RequestBody OrderCreateReq req) {
        return ApiResponse.of(orderService.create(req));
    }

    @GetMapping("/members/{memberId}/orders")
    public ApiResponse<PageRes<OrderRes>> listByMember(
            @PathVariable Long memberId,
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return ApiResponse.of(orderService.listByMember(memberId, pageable));
    }

    /**
     * DELETE /orders/{orderId} — 주문 취소
     * 인증 미구현 단계라 RequestParam으로 memberId를 받음
     */
    @DeleteMapping("/orders/{orderId}")
    public ApiResponse<Void> cancel(@PathVariable Long orderId,
                                    @RequestParam Long memberId) {
        orderService.cancel(orderId, memberId);
        return ApiResponse.of(null);
    }
}
