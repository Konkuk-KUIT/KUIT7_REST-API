package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemReq {

    @NotNull(message = "메뉴 ID는 필수입니다")
    private Long menuId;

    @NotNull(message = "수량은 필수입니다")
    @Positive(message = "수량은 0보다 커야 합니다")
    private Integer quantity;

    private List<Long> menuOptionIds;
}
