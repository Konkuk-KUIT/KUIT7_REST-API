package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.cart.Cart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CartListRes {
    private Long cartId;
    private String storeName;
    private String menuName;
    private Integer menuPrice;
    private Integer quantity;
    private Integer totalPrice;

    public static CartListRes from(Cart cart) {
        int price = cart.getMenu().getPrice();
        int qty = cart.getQuantity();

        return CartListRes.builder()
                .cartId(cart.getId())
                .storeName(cart.getStore().getName())
                .menuName(cart.getMenu().getName())
                .menuPrice(price)
                .quantity(qty)
                .totalPrice(price * qty)
                .build();
    }
}