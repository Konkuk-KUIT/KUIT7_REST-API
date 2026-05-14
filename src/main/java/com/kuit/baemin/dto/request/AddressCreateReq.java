package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AddressCreateReq {

    @NotBlank(message = "주소는 필수입니다.")
    @Size(max = 200, message = "주소는 200자 이하여야 합니다.")
    private String address;

    @NotBlank(message = "주소 이름은 필수입니다.")
    @Size(max = 30, message = "주소 이름은 30자 이하여야 합니다.")
    private String addressName;
}
