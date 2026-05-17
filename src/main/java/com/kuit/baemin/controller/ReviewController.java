package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.ReviewReq;
import com.kuit.baemin.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * POST /reviews — 리뷰 작성하기
     */
    @PostMapping
    public ApiResponse<Long> createReview(@Valid @RequestBody ReviewReq req) {
        return ApiResponse.of(reviewService.createReview(req));
    }
}