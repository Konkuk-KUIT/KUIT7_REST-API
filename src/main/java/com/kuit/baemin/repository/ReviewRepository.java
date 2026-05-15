package com.kuit.baemin.repository;

import com.kuit.baemin.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 특정 식당의 리뷰 중 특정 평점인 것들만 조회
    List<Review> findByRestaurantIdAndRate(Long restaurantId, Double rate);

    // 특정 식당의 모든 리뷰 조회
    List<Review> findByRestaurantId(Long restaurantId);
}