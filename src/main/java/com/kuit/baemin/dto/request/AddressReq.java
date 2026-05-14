package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressReq {

    @NotBlank(message = "주소 카테고리(우리집, 회사 등)는 필수입니다.")
    private String addressCategory;

    @NotNull(message = "기본 배달지 여부를 선택해주세요.")
    private Boolean isDefault;

    private String entrancePassword;

    @NotNull(message = "위도 정보가 필요합니다.")
    private BigDecimal userLatitude;

    @NotNull(message = "경도 정보가 필요합니다.")
    private BigDecimal userLongitude;


}
