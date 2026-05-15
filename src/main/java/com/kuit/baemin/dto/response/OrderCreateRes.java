package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.order.DeliveryOrder;
import com.kuit.baemin.domain.order.OrderMenu;
import com.kuit.baemin.domain.order.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderCreateRes {

    private Long orderId;
    private String orderNumber;
    private OrderStatus orderStatus;
    private Integer totalMenuCount;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;

    public static OrderCreateRes from(DeliveryOrder order) {
        return OrderCreateRes.builder()
                .orderId(order.getId())
                .orderNumber(order.getOrderNumber())
                .orderStatus(order.getOrderStatus())
                .totalMenuCount(order.getOrderMenus().size())
                .totalAmount(calculateTotalAmount(order))
                .createdAt(order.getCreatedAt())
                .build();
    }

    private static BigDecimal calculateTotalAmount(DeliveryOrder order) {
        BigDecimal menuTotal = order.getOrderMenus().stream()
                .map(OrderCreateRes::calculateMenuAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return menuTotal.add(order.getDeliveryFee());
    }

    private static BigDecimal calculateMenuAmount(OrderMenu orderMenu) {
        BigDecimal optionTotal = orderMenu.getOrderMenuOptions().stream()
                .map(option -> option.getAdditionalPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return orderMenu.getUnitPrice()
                .add(optionTotal)
                .multiply(BigDecimal.valueOf(orderMenu.getQuantity()));
    }
}
