package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Order.Order;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class OrderRes {

    private Long orderId;
    private String storeName;
    private String orderStatus;
    private LocalDateTime createdAt;
    private BigDecimal totalAmount; // 계산 로직 필요
    private List<OrderMenuDTO> orderMenus;

    public static OrderRes from(Order order) {
        return new OrderRes(
                order.getOrderId(),
                order.getStore().getStoreName(),
                order.getStatus().toString(),
                order.getCreatedAt(),
                BigDecimal.ZERO, // 서비스 레이어에서 계산 로직 추가 권장
                order.getOrderMenus().stream()
                        .map(OrderMenuDTO::from)
                        .toList()
        );
    }
}
