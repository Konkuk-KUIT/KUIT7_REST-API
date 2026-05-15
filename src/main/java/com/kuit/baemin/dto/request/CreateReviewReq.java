package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateReviewReq {

    @NotNull
    private Long memberId;

    @NotNull
    private Long orderId;

    @NotNull
    @Min(1) @Max(5)
    private Integer rate;

    private String content;
}