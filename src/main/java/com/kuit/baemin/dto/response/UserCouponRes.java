package com.kuit.baemin.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserCouponRes {
    private Long userCouponId;
    private String expireDay;
}
