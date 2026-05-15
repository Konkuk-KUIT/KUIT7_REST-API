package com.kuit.baemin.service;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.review.Review;
import com.kuit.baemin.domain.review.ReviewStatus;
import com.kuit.baemin.dto.request.ReviewReq;
import com.kuit.baemin.dto.response.ReviewRes;
import com.kuit.baemin.exception.GeneralException;
import com.kuit.baemin.exception.MemberException;
import com.kuit.baemin.exception.OrderException;
import com.kuit.baemin.exception.RestaurantException;
import com.kuit.baemin.exception.ReviewException;
import com.kuit.baemin.repository.MemberRepository;
import com.kuit.baemin.repository.RestaurantRepository;
import com.kuit.baemin.repository.ReviewRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.kuit.baemin.exception.errorcode.ErrorStatus.BAD_REQUEST;
import static com.kuit.baemin.exception.errorcode.ErrorStatus.MEMBER_NOT_FOUND;
import static com.kuit.baemin.exception.errorcode.ErrorStatus.RESTAURANT_NOT_FOUND;
import static com.kuit.baemin.exception.errorcode.ErrorStatus.REVIEW_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long createReview(ReviewReq req) {
        // 1. [예외 처리] 명세서 조건: 평점 범위가 1~5를 벗어나면 404 에러 (Custom Exception 호출)
        if (req.getRate() < 1 || req.getRate() > 5) {
            throw new GeneralException(BAD_REQUEST); // 404 역할을 하는 알림 문구
        }

        // 2. [예외 처리] 사용자 존재 확인 (404)
        Member member = memberRepository.findById(req.getMemberId())
                .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

        // 3. [예외 처리] 명세서 조건: 존재하지 않는 식당 ID인 경우 (404)
        Restaurant restaurant = restaurantRepository.findById(req.getRestaurantId())
                .orElseThrow(() -> new RestaurantException(RESTAURANT_NOT_FOUND));

        // 4. 리뷰 엔티티 생성
        Review review = Review.builder()
                .member(member)
                .restaurant(restaurant)
                .rate(req.getRate())
                .comment(req.getComment())
                .status(ReviewStatus.ACTIVE)
                .build();

        // 5. DB 저장 및 ID 반환
        Review savedReview = reviewRepository.save(review);
        return savedReview.getId();
    }

    public List<ReviewRes> getReviews(Long restaurantId, Double rate) {
        // 1. [예외 처리] 우선 식당 자체가 존재하는지 확인
        if (!restaurantRepository.existsById(restaurantId)) {
            throw new OrderException(RESTAURANT_NOT_FOUND);
        }

        List<Review> reviews;

        // 2. 평점(rate) 필터링 조건에 따른 조회
        if (rate != null) {
            reviews = reviewRepository.findByRestaurantIdAndRate(restaurantId, rate);
        } else {
            reviews = reviewRepository.findByRestaurantId(restaurantId);
        }

        // 3. [예외 처리] 명세서 조건: 검색 결과가 하나도 없는 경우 404 Not Found
        if (reviews.isEmpty()) {
            throw new ReviewException(REVIEW_NOT_FOUND);
        }

        return reviews.stream()
                .map(ReviewRes::from)
                .toList();
    }
}