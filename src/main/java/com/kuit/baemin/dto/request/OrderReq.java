package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderReq {
  @NotNull(message = "가게 ID는 필수입니다.")
  private Long restaurantId;

  @NotNull(message = "총 가격은 필수입니다.")
  @Min(value = 0, message = "가격은 0 이상이어야 합니다.")
  private Integer totalPrice;
}
