package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.cart.CartItem;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CartItemRes {

    private Long cartId;
    private Long memberId;
    private Long storeId;
    private Long menuId;
    private String menuName;
    private Integer price;
    private Integer quantity;
    private Integer totalPrice;

    public static CartItemRes from(CartItem cartItem) {
        return CartItemRes.builder()
                .cartId(cartItem.getId())
                .memberId(cartItem.getMember().getId())
                .storeId(cartItem.getRestaurant().getId())
                .menuId(cartItem.getMenu().getId())
                .menuName(cartItem.getMenu().getName())
                .price(cartItem.getMenu().getPrice())
                .quantity(cartItem.getQuantity())
                .totalPrice(cartItem.getTotalPrice())
                .build();
    }
}
