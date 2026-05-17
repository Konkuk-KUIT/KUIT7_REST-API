package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReviewReq {
    @NotNull(message = "가게 ID는 필수입니다.")
    private Long storeId;

    @NotNull(message = "회원 ID는 필수입니다.")
    private Long userId;

    private Long menuId;

    @NotNull(message = "별점은 필수입니다.")
    @Min(value = 1, message = "별점은 최소 1점 이상이어야 합니다.")
    @Max(value = 5, message = "별점은 최대 5점 이하이어야 합니다.")
    private Integer rating;

    private String content;
    private String reviewPictureUrl;
}