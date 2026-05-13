package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
