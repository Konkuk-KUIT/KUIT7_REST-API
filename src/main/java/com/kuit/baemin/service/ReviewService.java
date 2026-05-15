// ReviewService.java
package com.kuit.baemin.service;

import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.order.Order;
import com.kuit.baemin.domain.restaurant.Restaurant;
import com.kuit.baemin.domain.review.Review;
import com.kuit.baemin.dto.request.CreateReviewReq;
import com.kuit.baemin.dto.response.ReviewRes;
import com.kuit.baemin.exception.*;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public ReviewRes createReview(CreateReviewReq req) {
        Member member = memberRepository.findById(req.getMemberId())
                .orElseThrow(() -> new MemberException(ErrorStatus.MEMBER_NOT_FOUND));
        Order order = orderRepository.findById(req.getOrderId())
                .orElseThrow(() -> new OrderException(ErrorStatus.ORDER_NOT_FOUND));
        Restaurant restaurant = order.getRestaurant();

        Review review = Review.builder()
                .member(member)
                .order(order)
                .restaurant(restaurant)
                .rate(req.getRate())
                .content(req.getContent())
                .build();
        return ReviewRes.from(reviewRepository.save(review));
    }

    @Transactional
    public void deleteReview(Long reviewId, Long memberId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewException(ErrorStatus.REVIEW_NOT_FOUND));
        if (!review.getMember().getId().equals(memberId)) {
            throw new ReviewException(ErrorStatus.REVIEW_UNAUTHORIZED);
        }
        reviewRepository.delete(review);
    }
}