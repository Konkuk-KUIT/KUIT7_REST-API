package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderReq {
    @NotNull(message = "회원 ID는 필수입니다.")
    private Long userId;

    @NotNull(message = "가게 ID는 필수입니다.")
    private Long storeId;

    @NotBlank(message = "결제 수단을 선택해주세요.")
    private String paymentMethod;

    @NotNull(message = "총 주문 금액은 필수입니다.")
    private Integer totalPrice;

    private String requests;
}