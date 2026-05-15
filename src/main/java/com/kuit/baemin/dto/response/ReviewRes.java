package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.review.Review;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewRes {
    private Long id;
    private Long memberId;
    private Long restaurantId;
    private Integer rate;
    private String content;

    public static ReviewRes from(Review review) {
        return ReviewRes.builder()
                .id(review.getId())
                .memberId(review.getMember().getId())
                .restaurantId(review.getRestaurant().getId())
                .rate(review.getRate())
                .content(review.getContent())
                .build();
    }
}