package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReviewReq {

    @NotBlank
    private String content;

    @NotNull
    @Min(1) @Max(5)
    private Integer rating;

    @NotNull
    private Long userId;

    @NotNull
    private Long restaurantId;

    // 주문 ID는 'POST /orders/{id}/reviews' 에 있으므로 생략
}
