package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.CreateReviewReq;
import com.kuit.baemin.dto.response.ReviewRes;
import com.kuit.baemin.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Review", description = "리뷰 관련 API")
@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "리뷰 작성")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "작성 성공")
    @PostMapping
    public ApiResponse<ReviewRes> createReview(@Valid @RequestBody CreateReviewReq req) {
        return ApiResponse.of(reviewService.createReview(req));
    }

    @Operation(summary = "리뷰 삭제")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "삭제 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "삭제 권한 없음"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "리뷰 없음")
    })
    @DeleteMapping("/{reviewId}")
    public ApiResponse<Void> deleteReview(
            @Parameter(description = "리뷰 ID") @PathVariable Long reviewId,
            @Parameter(description = "회원 ID") @RequestParam Long memberId) {
        reviewService.deleteReview(reviewId, memberId);
        return ApiResponse.onSuccess(null);
    }
}