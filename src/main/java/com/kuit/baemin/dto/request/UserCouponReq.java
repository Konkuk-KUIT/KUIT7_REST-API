package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserCouponReq {
    @NotNull(message = "발급받을 쿠폰 ID를 입력해주세요.")
    private long couponId;
}
