package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.review.Review;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewDetailRes {
    private Long id;
    private String nickname;
    private String content;
    private Integer rating;

    // Entity -> DTO 변환 (LAZY 로딩된 Member의 닉네임도 안전하게 가져옵니다)
    public static ReviewDetailRes from(Review review) {
        return ReviewDetailRes.builder()
                .id(review.getId())
                .nickname(review.getMember().getNickname())
                .content(review.getContent())
                .rating(review.getRating())
                .build();
    }
}