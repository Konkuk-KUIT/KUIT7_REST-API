package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.CartCreateReq;
import com.kuit.baemin.dto.request.CartUpdateReq;
import com.kuit.baemin.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    // 6. 장바구니 담기
    @PostMapping
    public ApiResponse<Long> addToCart(@Valid @RequestBody CartCreateReq req) {
        return ApiResponse.onSuccess(cartService.addToCart(req));
    }

    // 7. 장바구니 수량/정보 수정
    @PatchMapping("/{cartId}")
    public ApiResponse<Long> updateCart(
            @PathVariable Long cartId,
            @Valid @RequestBody CartUpdateReq req) {
        return ApiResponse.onSuccess(cartService.updateCart(cartId, req));
    }

    // 8. 장바구니 품목 삭제
    @DeleteMapping("/{cartId}")
    public ApiResponse<String> deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
        return ApiResponse.onSuccess("삭제 성공");
    }
}
