package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.order.OrderItem;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OrderItemRes {

    private Long id;
    private String menuName;
    private Integer menuPrice;
    private Integer quantity;
    private List<OrderItemOptionRes> options;

    public static OrderItemRes of(OrderItem item, List<OrderItemOptionRes> options) {
        return OrderItemRes.builder()
                .id(item.getId())
                .menuName(item.getMenuName())
                .menuPrice(item.getMenuPrice())
                .quantity(item.getQuantity())
                .options(options)
                .build();
    }
}
