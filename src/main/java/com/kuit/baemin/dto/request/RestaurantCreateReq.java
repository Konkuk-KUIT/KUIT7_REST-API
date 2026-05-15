package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class RestaurantCreateReq {

    @NotNull(message = "카테고리 ID는 필수입니다.")
    private Long categoryId;

    @NotBlank(message = "가게 이름은 필수입니다.")
    @Size(max = 100, message = "가게 이름은 100자 이하여야 합니다.")
    private String name;

    @NotBlank(message = "전화번호는 필수입니다.")
    @Pattern(regexp = "^0\\d{1,2}-\\d{3,4}-\\d{4}$", message = "올바른 전화번호 형식이 아닙니다. ex) 02-1234-5678")
    private String phone;

    @NotBlank(message = "주소는 필수입니다.")
    @Size(max = 200, message = "주소는 200자 이하여야 합니다.")
    private String address;

    private BigDecimal latitude;
    private BigDecimal longitude;

    @NotNull(message = "최소 주문 금액은 필수입니다.")
    @PositiveOrZero(message = "최소 주문 금액은 0 이상이어야 합니다.")
    private Integer minOrderPrice;

    @NotNull(message = "배달비는 필수입니다.")
    @PositiveOrZero(message = "배달비는 0 이상이어야 합니다.")
    private Integer deliveryFee;
}
