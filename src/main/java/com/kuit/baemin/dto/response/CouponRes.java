package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.coupon.CouponStatus;
import com.kuit.baemin.domain.member_coupon.MemberCoupon;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CouponRes {
    private Long id;
    private String name;
    private double rate;
    private CouponStatus status;
    private LocalDateTime createdAt;

    public static CouponRes from(MemberCoupon mc) {
        return CouponRes.builder()
                .id(mc.getId())
                .name(mc.getCoupon().getName())
                .rate(mc.getCoupon().getRate())
                .status(mc.getCoupon().getStatus())
                .createdAt(mc.getCreatedAt())
                .build();
    }
}