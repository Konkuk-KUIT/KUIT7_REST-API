package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCancelReq {
    @NotNull(message = "회원 ID는 필수 입력값입니다.")
    private Long memberId;
}