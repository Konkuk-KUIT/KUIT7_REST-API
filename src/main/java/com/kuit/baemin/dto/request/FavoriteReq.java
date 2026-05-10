package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteReq {
  @NotNull(message = "가게 ID는 필수입니다.")
  private Long restaurantId;
}
