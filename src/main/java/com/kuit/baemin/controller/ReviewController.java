package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.domain.review.Review;
import com.kuit.baemin.dto.request.ReviewReq;
import com.kuit.baemin.dto.response.ReviewListRes;
import com.kuit.baemin.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/reviews")
    public ApiResponse<Long> createReview(@Valid @RequestBody ReviewReq req) {
        return ApiResponse.of(reviewService.createReview(req));
    }

    // 간단하게 Entity Page를 그대로 반환하도록 작성 (실무에선 DTO 변환 필요)
    @GetMapping("/restaurants/{restaurantId}/reviews")
    public ApiResponse<ReviewListRes> getReviews(@PathVariable Long restaurantId,
                                                 @PageableDefault(size = 10) Pageable pageable) {
        return ApiResponse.of(reviewService.getReviews(restaurantId, pageable));
    }
}