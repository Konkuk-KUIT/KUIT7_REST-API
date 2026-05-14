package com.kuit.baemin.service;

import com.kuit.baemin.domain.coupon.Coupon;
import com.kuit.baemin.domain.coupon.UserCoupon;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.member.MemberStatus;
import com.kuit.baemin.dto.request.UserCouponReq;
import com.kuit.baemin.dto.response.UserCouponRes;
import com.kuit.baemin.exception.MemberException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.exception.handler.CouponException;
import com.kuit.baemin.repository.CouponRepository;
import com.kuit.baemin.repository.MemberRepository;
import com.kuit.baemin.repository.StoreRepository;
import com.kuit.baemin.repository.UserCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;
    private final UserCouponRepository userCouponRepository;

    @Transactional
    public UserCouponRes issueCoupon(Long memberId, UserCouponReq req){

        Member member = memberRepository.findByIdAndStatus(memberId, MemberStatus.ACTIVE)
                .orElseThrow(() -> new MemberException(ErrorStatus.MEMBER_NOT_FOUND));
        Coupon coupon = couponRepository.findByIdAndStatus(req.getCouponId(), "ACTIVE")
                .orElseThrow(() -> new CouponException(ErrorStatus.COUPON_NOT_FOUND));

        if (userCouponRepository.existsByMemberAndCouponAndStatus(member, coupon, "ACTIVE")) {
            throw new CouponException(ErrorStatus.COUPON_ALREADY_ISSUED);
        }

        LocalDateTime now = LocalDateTime.now();
        String expireDay = now.plusDays(30).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        UserCoupon userCoupon = UserCoupon.builder()
                .member(member)
                .coupon(coupon)
                .expireDay(expireDay)
                .isUsed(false)
                .build();

        userCouponRepository.save(userCoupon);

        return UserCouponRes.builder()
                .userCouponId(userCoupon.getId())
                .expireDay(expireDay)
                .build();

    }


}
