package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class MenuCreateReq {

    @NotBlank(message = "메뉴 이름은 필수입니다.")
    @Size(max = 100, message = "메뉴 이름은 100자 이하여야 합니다.")
    private String name;

    @Size(max = 1000, message = "설명은 1000자 이하여야 합니다.")
    private String description;

    @NotNull(message = "가격은 필수입니다.")
    @Positive(message = "가격은 1 이상이어야 합니다.")
    private Integer price;
}
