package com.kuit.baemin.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateAddressReq {
    @Size(max = 50, message = "주소 이름은 50자 이하여야 합니다.")
    private String addressName;

    @NotBlank(message = "주소는 필수 입력값입니다.")
    @Size(max = 255, message = "주소는 255자 이하여야 합니다.")
    private String address;

    @NotNull(message = "기본 주소 여부는 필수입니다.")
    private Boolean isDefault;

    @NotNull(message = "위도는 필수입니다.")
    private Double latitude;

    @NotNull(message = "경도는 필수입니다.")
    private Double longitude;

}
