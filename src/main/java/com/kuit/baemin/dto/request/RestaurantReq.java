package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantReq {

    @NotBlank(message = "식당 이름은 필수입니다")
    private String name;

    @NotBlank(message = "전화번호는 필수입니다")
    private String phoneNumber;

    @NotBlank(message = "도로명 주소는 필수입니다")
    private String roadAddress;

    @NotBlank(message = "상세주소는 필수입니다")
    private String detailAddress;

    @NotNull(message = "위도는 필수입니다")
    private BigDecimal latitude;

    @NotNull(message = "경도는 필수입니다")
    private BigDecimal longitude;

    @NotNull(message = "최소 주문 금액은 필수입니다")
    @Positive(message = "최소 주문 금액은 0보다 커야 합니다")
    private Long minOrderAmount;

    @NotNull(message = "배달료는 필수입니다")
    @Positive(message = "배달료는 0보다 커야 합니다")
    private Long deliveryFee;
}
