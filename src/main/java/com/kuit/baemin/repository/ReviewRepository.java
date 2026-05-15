package com.kuit.baemin.repository;

import com.kuit.baemin.domain.review.Review;
import com.kuit.baemin.domain.review.ReviewStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    boolean existsByOrderId(Long orderId);

    List<Review> findByMemberIdAndStatus(Long memberId, ReviewStatus status);
}
