package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.CartItemCreateReq;
import com.kuit.baemin.dto.request.CartItemUpdateReq;
import com.kuit.baemin.dto.response.CartItemRes;
import com.kuit.baemin.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart-items")
@RequiredArgsConstructor
@Validated
public class CartController {

    private final CartService cartService;

    @Operation(summary = "장바구니에 메뉴 추가", description = "회원의 장바구니에 메뉴를 추가한다.")
    @PostMapping
    public ApiResponse<CartItemRes> createCartItem(@Valid @RequestBody CartItemCreateReq req) {
        return ApiResponse.of(cartService.createCartItem(req));
    }

    @Operation(summary = "장바구니 수량 변경", description = "장바구니 항목의 수량을 변경한다.")
    @PatchMapping("/{cartId}")
    public ApiResponse<CartItemRes> updateCartItem(
            @PathVariable @Positive Long cartId,
            @Valid @RequestBody CartItemUpdateReq req
    ) {
        return ApiResponse.of(cartService.updateCartItem(cartId, req));
    }

    @Operation(summary = "장바구니 항목 삭제", description = "장바구니 항목을 삭제한다.")
    @DeleteMapping("/{cartId}")
    public ApiResponse<Void> deleteCartItem(@PathVariable @Positive Long cartId) {
        cartService.deleteCartItem(cartId);
        return ApiResponse.of(null);
    }
}
