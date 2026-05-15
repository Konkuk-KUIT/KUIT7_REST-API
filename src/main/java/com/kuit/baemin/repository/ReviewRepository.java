package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Review;
import com.kuit.baemin.domain.order.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
