package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.review.Review;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewCreateRes {

    private Long reviewId;

    public static ReviewCreateRes from(Review review) {
        return ReviewCreateRes.builder()
                .reviewId(review.getId())
                .build();
    }
}
