package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class AddressReq {
    @NotNull(message = "사용자 ID는 필수입니다.")
    private Long memberId;

    @NotBlank(message = "주소 이름은 필수입니다.")
    private String name;

    @NotBlank(message = "카테고리는 필수입니다.")
    private String category;
}