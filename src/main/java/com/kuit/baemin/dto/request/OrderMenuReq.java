package com.kuit.baemin.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderMenuReq {

    @NotNull(message = "메뉴 ID는 필수입니다.")
    private Long menuId;

    @Valid
    @NotEmpty(message = "옵션을 최소 하나 이상 선택해주세요.")
    private List<Long> optionIds;
}
