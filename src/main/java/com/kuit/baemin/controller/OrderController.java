package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.OrderReq;
import com.kuit.baemin.dto.response.OrderRes;
import com.kuit.baemin.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "주문(Order)", description = "주문 관련 API")
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    /**
     * API 8: POST /orders — 주문 생성
     */
    @Operation(summary = "주문 생성", description = "새로운 주문을 생성합니다. X-User-Id 헤더에 사용자 ID를 포함해야 합니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "주문 생성 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "사용자 또는 식당을 찾을 수 없음"),
    })
    @PostMapping
    public ResponseEntity<ApiResponse<Long>> createOrder(
            @Parameter(description = "사용자 ID", example = "1", required = true)
            @RequestHeader("X-User-Id") Long userId,
            @Valid @RequestBody OrderReq req) {

        Long orderId = orderService.createOrder(userId, req);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.onSuccess(orderId));
    }

    /**
     * API 9: GET /orders/{orderId} — 주문 상세 조회
     */
    @Operation(summary = "주문 상세 조회", description = "특정 주문의 상세 정보를 조회합니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "주문을 찾을 수 없음"),
    })
    @GetMapping("/{orderId}")
    public ResponseEntity<ApiResponse<OrderRes>> getOrderById(
            @Parameter(description = "주문 ID", example = "1")
            @PathVariable Long orderId) {

        OrderRes order = orderService.getOrderById(orderId);
        return ResponseEntity.ok(ApiResponse.onSuccess(order));
    }

    /**
     * API 10: GET /users/{userId}/orders — 사용자 주문 목록 조회
     */
    @Operation(summary = "사용자 주문 목록 조회", description = "특정 사용자의 주문 목록을 페이징으로 조회합니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없음"),
    })
    @GetMapping("/users/{userId}/orders")
    public ResponseEntity<ApiResponse<Page<OrderRes>>> getOrdersByUser(
            @Parameter(description = "사용자 ID", example = "1")
            @PathVariable Long userId,
            @Parameter(description = "페이지 번호 (0부터 시작)", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 크기", example = "20")
            @RequestParam(defaultValue = "20") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<OrderRes> orders = orderService.getOrdersByUser(userId, pageable);
        return ResponseEntity.ok(ApiResponse.onSuccess(orders));
    }
}
