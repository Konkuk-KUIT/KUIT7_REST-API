package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.CreateReviewReq;
import com.kuit.baemin.dto.response.ReviewRes;
import com.kuit.baemin.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    /** POST /reviews — 리뷰 작성 */
    @PostMapping
    public ApiResponse<ReviewRes> createReview(@Valid @RequestBody CreateReviewReq req) {
        return ApiResponse.of(reviewService.createReview(req));
    }

    /** DELETE /reviews/{reviewId}?memberId={memberId} — 리뷰 삭제 */
    @DeleteMapping("/{reviewId}")
    public ApiResponse<Void> deleteReview(
            @PathVariable Long reviewId,
            @RequestParam Long memberId) {
        reviewService.deleteReview(reviewId, memberId);
        return ApiResponse.onSuccess(null);
    }
}