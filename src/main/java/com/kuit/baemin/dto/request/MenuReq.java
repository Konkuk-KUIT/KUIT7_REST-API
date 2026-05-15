package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MenuReq {

    @NotBlank(message = "메뉴 이름은 필수입니다")
    private String name;

    private String description;

    @NotNull(message = "가격은 필수입니다")
    @Positive(message = "가격은 0보다 커야 합니다")
    private Long price;
}
