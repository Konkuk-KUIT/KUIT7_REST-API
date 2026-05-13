package com.kuit.baemin.service;

import com.kuit.baemin.domain.Review.Review;
import com.kuit.baemin.domain.Review.ReviewStatus;
import com.kuit.baemin.dto.request.ReviewReq;
import com.kuit.baemin.exception.OrderException;
import com.kuit.baemin.exception.RestaurantException;
import com.kuit.baemin.exception.ReviewException;
import com.kuit.baemin.exception.UserException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.OrderRepository;
import com.kuit.baemin.repository.RestaurantRepository;
import com.kuit.baemin.repository.ReviewRepository;
import com.kuit.baemin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kuit.baemin.exception.errorcode.ErrorStatus.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderRepository orderRepository;

    /**
     * 리뷰 작성
     */
    @Transactional
    public Long save(ReviewReq req, Long orderId) {

        Review review = Review.builder()
                .content(req.getContent())
                .rating(req.getRating())
                .status(ReviewStatus.ACTIVE)
                .user(userRepository.findById(req.getUserId())
                        .orElseThrow(() -> new UserException(USER_NOT_FOUND)))
                .restaurant(restaurantRepository.findById(req.getRestaurantId())
                        .orElseThrow(() -> new RestaurantException(RESTAURANT_NOT_FOUND)))
                .order(orderRepository.findById(orderId)
                        .orElseThrow(() -> new OrderException(ORDER_NOT_FOUND)))
                .build();

        Review saved = reviewRepository.save(review);
        return saved.getId();
    }

    /**
     * 리뷰 삭제
     */
    @Transactional
    public void delete(Long reviewId) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewException(REVIEW_NOT_FOUND));

        review.updateStatus(ReviewStatus.INACTIVE);
    }
}
