package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class CartItemCreateReq {

    @NotNull(message = "회원 ID는 필수입니다.")
    @Positive(message = "회원 ID는 양수여야 합니다.")
    private Long memberId;

    @NotNull(message = "가게 ID는 필수입니다.")
    @Positive(message = "가게 ID는 양수여야 합니다.")
    private Long storeId;

    @NotNull(message = "메뉴 ID는 필수입니다.")
    @Positive(message = "메뉴 ID는 양수여야 합니다.")
    private Long menuId;

    @NotNull(message = "수량은 필수입니다.")
    @Positive(message = "수량은 1 이상이어야 합니다.")
    private Integer quantity;
}
