package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.order.DeliveryOrder;
import com.kuit.baemin.domain.order.OrderMenu;
import com.kuit.baemin.domain.order.OrderMenuOption;
import com.kuit.baemin.domain.order.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderDetailRes {

    private Long orderId;
    private String orderNumber;
    private OrderStatus orderStatus;
    private String requestMessage;
    private BigDecimal deliveryFee;
    private String deliveryRoadAddress;
    private String deliveryDetailAddress;
    private Long memberId;
    private Long restaurantId;
    private Long addressId;
    private List<OrderMenuRes> orderItems;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;

    public static OrderDetailRes from(DeliveryOrder order) {
        List<OrderMenuRes> orderItems = order.getOrderMenus().stream()
                .map(OrderMenuRes::from)
                .toList();

        return OrderDetailRes.builder()
                .orderId(order.getId())
                .orderNumber(order.getOrderNumber())
                .orderStatus(order.getOrderStatus())
                .requestMessage(order.getRequestMessage())
                .deliveryFee(order.getDeliveryFee())
                .deliveryRoadAddress(order.getDeliveryRoadAddress())
                .deliveryDetailAddress(order.getDeliveryDetailAddress())
                .memberId(order.getMember().getId())
                .restaurantId(order.getRestaurant().getId())
                .addressId(order.getAddress().getId())
                .orderItems(orderItems)
                .totalAmount(calculateTotalAmount(order))
                .createdAt(order.getCreatedAt())
                .build();
    }

    private static BigDecimal calculateTotalAmount(DeliveryOrder order) {
        BigDecimal menuTotal = order.getOrderMenus().stream()
                .map(OrderDetailRes::calculateMenuAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return menuTotal.add(order.getDeliveryFee());
    }

    private static BigDecimal calculateMenuAmount(OrderMenu orderMenu) {
        BigDecimal optionTotal = orderMenu.getOrderMenuOptions().stream()
                .map(OrderMenuOption::getAdditionalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return orderMenu.getUnitPrice()
                .add(optionTotal)
                .multiply(BigDecimal.valueOf(orderMenu.getQuantity()));
    }

    @Getter
    @Builder
    public static class OrderMenuRes {

        private Long orderMenuId;
        private Long menuId;
        private String menuName;
        private BigDecimal unitPrice;
        private Integer quantity;
        private List<OrderMenuOptionRes> options;

        public static OrderMenuRes from(OrderMenu orderMenu) {
            return OrderMenuRes.builder()
                    .orderMenuId(orderMenu.getId())
                    .menuId(orderMenu.getMenuId())
                    .menuName(orderMenu.getMenuName())
                    .unitPrice(orderMenu.getUnitPrice())
                    .quantity(orderMenu.getQuantity())
                    .options(orderMenu.getOrderMenuOptions().stream()
                            .map(OrderMenuOptionRes::from)
                            .toList())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class OrderMenuOptionRes {

        private Long orderMenuOptionId;
        private Long menuOptionId;
        private String optionGroupName;
        private String optionName;
        private BigDecimal additionalPrice;

        public static OrderMenuOptionRes from(OrderMenuOption option) {
            return OrderMenuOptionRes.builder()
                    .orderMenuOptionId(option.getId())
                    .menuOptionId(option.getMenuOptionId())
                    .optionGroupName(option.getOptionGroupName())
                    .optionName(option.getOptionName())
                    .additionalPrice(option.getAdditionalPrice())
                    .build();
        }
    }
}
