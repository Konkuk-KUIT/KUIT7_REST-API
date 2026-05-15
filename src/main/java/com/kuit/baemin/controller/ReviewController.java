package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.ReviewCreateReq;
import com.kuit.baemin.dto.response.ReviewRes;
import com.kuit.baemin.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * POST /reviews — 리뷰 작성
     */
    @PostMapping("/reviews")
    public ApiResponse<Long> createReview(@Valid @RequestBody ReviewCreateReq req) {
        return ApiResponse.of(reviewService.createReview(req));
    }

    /**
     * GET /members/{memberId}/reviews — 회원 리뷰 목록 조회
     */
    @GetMapping("/members/{memberId}/reviews")
    public ApiResponse<List<ReviewRes>> getMemberReviews(
            @PathVariable("memberId") Long memberId
    ) {
        return ApiResponse.of(reviewService.getMemberReviews(memberId));
    }
}
