package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.review.Review;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewRes {

    private Long id;
    private Long orderId;
    private String restaurantName;
    private Integer rating;
    private String content;
    private LocalDateTime createdAt;

    public static ReviewRes from(Review review) {
        return ReviewRes.builder()
                .id(review.getId())
                .orderId(review.getOrder().getId())
                .restaurantName(review.getRestaurant().getName())
                .rating(review.getRating())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
