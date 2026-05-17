package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.CartReq;
import com.kuit.baemin.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    /**
     * POST /carts — 장바구니에 메뉴 담기
     */
    @PostMapping
    public ApiResponse<Long> addToCart(@Valid @RequestBody CartReq req) {
        return ApiResponse.of(cartService.addToCart(req));
    }

    /**
     * DELETE /carts/{cartId} — 장바구니 항목 삭제
     */
    @DeleteMapping("/{cartId}")
    public ApiResponse<String> deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
        return ApiResponse.of("장바구니 항목이 성공적으로 삭제되었습니다.");
    }

    /**
     * GET /carts/{userId} — 내 장바구니 목록 조회
     */
    @GetMapping("/{userId}")
    public ApiResponse<java.util.List<com.kuit.baemin.dto.response.CartListRes>> getCartList(@PathVariable Long userId) {
        return ApiResponse.of(cartService.getCartList(userId));
    }
}