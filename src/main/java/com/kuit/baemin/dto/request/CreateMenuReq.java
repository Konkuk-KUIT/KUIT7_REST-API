package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateMenuReq {

    @NotBlank(message = "메뉴 이름은 필수입니다.")
    private String menuName;

    private String description;

    @NotNull(message = "가격은 필수입니다.")
    private Integer price;
}