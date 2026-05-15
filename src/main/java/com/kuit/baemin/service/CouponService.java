package com.kuit.baemin.service;

import com.kuit.baemin.domain.member_coupon.MemberCoupon;
import com.kuit.baemin.dto.response.CouponRes;
import com.kuit.baemin.exception.CouponException;
import com.kuit.baemin.exception.GeneralException;
import com.kuit.baemin.repository.MemberCouponRepository;
import com.kuit.baemin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kuit.baemin.exception.errorcode.ErrorStatus.COUPON_NOT_FOUND;
import static com.kuit.baemin.exception.errorcode.ErrorStatus.FORBIDDEN;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final MemberCouponRepository memberCouponRepository;
    private final MemberRepository memberRepository;

    public List<CouponRes> getMyCoupons(Long memberId, Long loginMemberId, String category) {
        // 1. 본인 확인 (403)
        if (!memberId.equals(loginMemberId)) {
            throw new GeneralException(FORBIDDEN);
        }

        // 2. 우선 해당 회원의 모든 멤버 쿠폰을 DB에서 조회
        List<MemberCoupon> coupons = memberCouponRepository.findByMemberId(memberId);

        // 3. 카테고리 파라미터가 들어온 경우에만 자바 코드로 쿠폰 내부의 카테고리 필터링
        if (category != null && !category.isEmpty()) {
            coupons = coupons.stream()
                    .filter(mc -> mc.getCoupon() != null &&
                            mc.getCoupon().getCategory() != null &&
                            category.equals(mc.getCoupon().getCategory()))
                    .toList();
        }

        // 4. 최종 결과 없음 예외 처리 (404)
        // 처음부터 쿠폰이 없었거나, 필터링 후 남은 쿠폰이 없는 경우 모두 여기서 걸러집니다.
        if (coupons.isEmpty()) {
            throw new CouponException(COUPON_NOT_FOUND);
        }

        return coupons.stream()
                .map(CouponRes::from)
                .toList();
    }
}