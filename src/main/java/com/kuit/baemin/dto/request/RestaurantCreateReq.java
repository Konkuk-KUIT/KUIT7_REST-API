package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.Getter;

@Getter
public class RestaurantCreateReq {

    @NotBlank(message = "가게명은 필수입니다.")
    @Size(max = 100, message = "가게명은 100자 이하여야 합니다.")
    private String name;

    @Size(max = 500, message = "가게 설명은 500자 이하여야 합니다.")
    private String description;

    @NotBlank(message = "가게 전화번호는 필수입니다.")
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "올바른 전화번호 형식이 아닙니다. ex) 02-1234-5678")
    private String phoneNumber;

    @NotBlank(message = "도로명 주소는 필수입니다.")
    @Size(max = 100, message = "도로명 주소는 100자 이하여야 합니다.")
    private String roadAddress;

    @Size(max = 100, message = "상세 주소는 100자 이하여야 합니다.")
    private String detailAddress;

    @NotNull(message = "위도는 필수입니다.")
    private BigDecimal latitude;

    @NotNull(message = "경도는 필수입니다.")
    private BigDecimal longitude;

    @NotNull(message = "최소 주문 금액은 필수입니다.")
    @DecimalMin(value = "0.00", message = "최소 주문 금액은 0원 이상이어야 합니다.")
    private BigDecimal minOrderAmount;

    @NotNull(message = "배달팁은 필수입니다.")
    @DecimalMin(value = "0.00", message = "배달팁은 0원 이상이어야 합니다.")
    private BigDecimal deliveryFee;

    @Size(max = 2000, message = "이미지 URL은 2000자 이하여야 합니다.")
    private String imageUrl;
}
