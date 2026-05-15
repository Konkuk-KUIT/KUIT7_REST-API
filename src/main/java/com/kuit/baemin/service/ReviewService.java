package com.kuit.baemin.service;

import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.order.Orders;
import com.kuit.baemin.domain.review.Review;
import com.kuit.baemin.dto.request.ReviewReq;
import com.kuit.baemin.dto.response.ReviewDetailRes;
import com.kuit.baemin.dto.response.ReviewListRes;
import com.kuit.baemin.exception.GeneralException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.MemberRepository;
import com.kuit.baemin.repository.OrdersRepository;
import com.kuit.baemin.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final OrdersRepository ordersRepository;

    /**
     * API 9: 리뷰 작성
     */
    @Transactional
    public Long createReview(ReviewReq req) {
        Member member = memberRepository.findById(req.getMemberId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));
        Orders order = ordersRepository.findById(req.getOrderId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.ORDER_NOT_FOUND));

        Review review = Review.builder()
                .member(member)
                .orders(order)
                .content(req.getContent())
                .rating(req.getRating())
                .build();

        return reviewRepository.save(review).getId();
    }

    /**
     * API 10: 가게 리뷰 목록 조회 (우리가 만든 JOIN 쿼리 사용)
     */
    public ReviewListRes getReviews(Long restaurantId, Pageable pageable) {
        // 1. DB에서 리뷰 엔티티 페이지를 가져옵니다.
        Page<Review> reviewPage = reviewRepository.findReviewsByRestaurantId(restaurantId, pageable);

        // 2. Entity를 DTO로 싹 변환합니다.
        java.util.List<ReviewDetailRes> dtoList = reviewPage.getContent().stream()
                .map(ReviewDetailRes::from)
                .collect(java.util.stream.Collectors.toList());

        // 3. 페이징 정보와 함께 묶어서 반환합니다.
        return ReviewListRes.builder()
                .reviews(dtoList)
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .build();
    }
}