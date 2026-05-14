package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AddressReq {
    @NotBlank(message = "주소는 필수 입력값입니다.")
    private String name;
}