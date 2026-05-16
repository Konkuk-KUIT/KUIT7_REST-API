package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRes {
  private Long id;
  private String restaurantName;
  private Integer totalPrice;
  private LocalDateTime createdAt;

  public static OrderRes from(Order order) {
    return new OrderRes(
        order.getId(),
        order.getStore().getName(),
        order.getTotalPrice(),
        order.getCreatedAt()
    );
  }
}
