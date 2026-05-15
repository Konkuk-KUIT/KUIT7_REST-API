package com.kuit.baemin.service;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.order.Orders;
import com.kuit.baemin.domain.review.Review;
import com.kuit.baemin.domain.review.ReviewStatus;
import com.kuit.baemin.dto.request.ReviewCreateReq;
import com.kuit.baemin.dto.response.ReviewRes;
import com.kuit.baemin.exception.GeneralException;
import com.kuit.baemin.exception.MemberException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.MemberRepository;
import com.kuit.baemin.repository.OrderRepository;
import com.kuit.baemin.repository.RestaurantRepository;
import com.kuit.baemin.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public Long createReview(ReviewCreateReq req) {
        Member member = memberRepository.findById(req.getMemberId())
                .orElseThrow(() -> new MemberException(ErrorStatus.MEMBER_NOT_FOUND));

        Restaurant restaurant = restaurantRepository.findById(req.getRestaurantId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND));

        Orders order = orderRepository.findById(req.getOrderId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.ORDER_NOT_FOUND));

        if (reviewRepository.existsByOrderId(req.getOrderId())) {
            throw new GeneralException(ErrorStatus.DUPLICATE_REVIEW);
        }

        Review review = Review.builder()
                .member(member)
                .restaurant(restaurant)
                .order(order)
                .rating(req.getRating())
                .content(req.getContent())
                .status(ReviewStatus.ACTIVE)
                .build();

        return reviewRepository.save(review).getId();
    }

    public List<ReviewRes> getMemberReviews(Long memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new MemberException(ErrorStatus.MEMBER_NOT_FOUND);
        }

        return reviewRepository.findByMemberIdAndStatus(memberId, ReviewStatus.ACTIVE)
                .stream()
                .map(ReviewRes::from)
                .toList();
    }
}
