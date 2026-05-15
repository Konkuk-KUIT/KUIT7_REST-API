package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateAddressReq {

    @NotBlank(message = "도로명 주소는 필수입니다.")
    private String roadAddress;

    private String detailAddress;

    private Boolean isDefault = false;
}