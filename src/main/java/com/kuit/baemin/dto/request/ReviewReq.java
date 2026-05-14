package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewReq {

    @NotNull(message = "사용자 ID는 필수입니다.")
    private Long memberId;

    @NotNull(message = "식당 ID는 필수입니다.")
    private Long restaurantId;

    @NotNull(message = "평점은 필수입니다.")
    @Min(value = 1, message = "평점은 최소 1점 이상이어야 합니다.")
    @Max(value = 5, message = "평점은 최대 5점 이하여야 합니다.")
    private Integer rate; // 400 Bad Request 및 범위 검증용

    @Size(max = 255, message = "리뷰 내용은 255자 이하로 작성해주세요.")
    private String comment;
}