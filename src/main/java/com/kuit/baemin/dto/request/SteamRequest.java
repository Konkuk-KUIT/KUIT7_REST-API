package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

@Getter
public class SteamRequest {
    @PositiveOrZero(message = "사용자 ID 는 0 이상입니다.")
    private Long memberId;

    @PositiveOrZero(message = "가게 ID 는 0 이상입니다.")
    private Long restaurantId;
}
