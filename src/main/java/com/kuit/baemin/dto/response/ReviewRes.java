package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.member.MemberStatus;
import com.kuit.baemin.domain.order.Order;
import com.kuit.baemin.domain.review.Review;
import com.kuit.baemin.domain.review.ReviewStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewRes {

    private Long id;
    private String memberName;
    private String restaurantName;
    private double rate;
    private String comment;
    private ReviewStatus status;
    private LocalDateTime createdAt;

    // 엔티티 → DTO 변환
    public static ReviewRes from(Review review) {
        return ReviewRes.builder()
                .id(review.getId())
                .memberName(review.getMember().getName())
                .restaurantName(review.getRestaurant().getName())
                .rate(review.getRate())
                .comment(review.getComment())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
