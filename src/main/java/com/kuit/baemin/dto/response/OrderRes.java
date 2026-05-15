package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.member.MemberStatus;
import com.kuit.baemin.domain.order.Order;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class OrderRes {

    private Long id;
    private String memberName;
    private String restaurantName;
    private String request;
    private MemberStatus status;
    private LocalDateTime orderDate;
    private LocalDateTime createdAt;

    // 엔티티 → DTO 변환
    public static OrderRes from(Order order) {
        return OrderRes.builder()
                .id(order.getId())
                .memberName(order.getMember().getName())
                .restaurantName(order.getRestaurant().getName())
                .request(order.getRequest())
                .orderDate(order.getOrderDate())
                .createdAt(order.getCreatedAt())
                .build();
    }
}
