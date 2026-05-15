package com.kuit.baemin.service;

import static com.kuit.baemin.exception.errorcode.ErrorStatus.MEMBER_NOT_FOUND;
import static com.kuit.baemin.exception.errorcode.ErrorStatus.ORDER_NOT_FOUND;

import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.order.DeliveryOrder;
import com.kuit.baemin.domain.review.Review;
import com.kuit.baemin.domain.review.ReviewStatus;
import com.kuit.baemin.dto.request.ReviewCreateReq;
import com.kuit.baemin.dto.response.ReviewCreateRes;
import com.kuit.baemin.exception.MemberException;
import com.kuit.baemin.exception.OrderException;
import com.kuit.baemin.repository.DeliveryOrderRepository;
import com.kuit.baemin.repository.MemberRepository;
import com.kuit.baemin.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final MemberRepository memberRepository;
    private final DeliveryOrderRepository deliveryOrderRepository;
    private final ReviewRepository reviewRepository;

    @Transactional
    public ReviewCreateRes createReview(ReviewCreateReq req) {
        Member member = memberRepository.findById(req.getMemberId())
                .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));
        DeliveryOrder order = deliveryOrderRepository.findById(req.getOrderId())
                .orElseThrow(() -> new OrderException(ORDER_NOT_FOUND));

        Review review = Review.builder()
                .rating(req.getRating())
                .content(req.getContent())
                .status(ReviewStatus.active)
                .member(member)
                .deliveryOrder(order)
                .build();

        return ReviewCreateRes.from(reviewRepository.save(review));
    }
}
