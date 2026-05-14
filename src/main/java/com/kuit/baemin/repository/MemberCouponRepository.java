package com.kuit.baemin.repository;

import com.kuit.baemin.domain.member_coupon.MemberCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberCouponRepository extends JpaRepository<MemberCoupon, Long> {
    // 특정 사용자가 가진 모든 쿠폰 조회
    List<MemberCoupon> findByMemberId(Long memberId);
}