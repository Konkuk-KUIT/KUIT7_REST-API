package com.kuit.baemin.repository;

import com.kuit.baemin.domain.coupon.Coupon;
import com.kuit.baemin.domain.coupon.UserCoupon;
import com.kuit.baemin.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCouponRepository extends JpaRepository<UserCoupon,Long> {
    boolean existsByMemberAndCouponAndStatus(Member member, Coupon coupon, String active);
}
