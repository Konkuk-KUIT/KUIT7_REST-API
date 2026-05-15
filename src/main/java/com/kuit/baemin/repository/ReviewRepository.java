package com.kuit.baemin.repository;

import com.kuit.baemin.domain.review.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 리뷰에는 가게 ID가 직접 없고 주문(Orders)에 연결되어 있으므로, 직접 쿼리짜줌
    @Query("SELECT r FROM Review r JOIN r.orders o WHERE o.restaurant.id = :restaurantId")
    Page<Review> findReviewsByRestaurantId(@Param("restaurantId") Long restaurantId, Pageable pageable);
}