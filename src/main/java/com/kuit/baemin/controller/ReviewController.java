package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.ReviewReq;
import com.kuit.baemin.dto.response.ReviewRes;
import com.kuit.baemin.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // 2. 추가
@RequestMapping("/reviews") // 3. 공통 주소 설정
@RequiredArgsConstructor // 4. 추가
public class ReviewController {

    private final ReviewService reviewService;

    /**
     * POST /reviews — 리뷰 작성
     */
    @PostMapping
    public ApiResponse<Long> createReview(@Valid @RequestBody ReviewReq req) {
        return ApiResponse.of(reviewService.createReview(req));
    }

    /**
     * GET /reviews/{restaurantId} 리뷰 조회
     */
    @GetMapping("/{restaurantId}")
    public ApiResponse<List<ReviewRes>> getReviews(@PathVariable Long restaurantId, @RequestParam(required = false) Double rate) {
        return ApiResponse.of(reviewService.getReviews(restaurantId, rate));
    }
}
