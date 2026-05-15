package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.RestaurantReq;
import com.kuit.baemin.dto.response.RestaurantRes;
import com.kuit.baemin.service.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "식당(Restaurant)", description = "식당 정보 관련 API")
@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    /**
     * API 1: POST /restaurants — 식당 생성
     */
    @Operation(summary = "식당 생성", description = "새로운 식당을 등록합니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "식당 생성 성공", content = @Content(schema = @Schema(implementation = Long.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 요청"),
    })
    @PostMapping
    public ResponseEntity<ApiResponse<Long>> createRestaurant(@Valid @RequestBody RestaurantReq req) {
        Long restaurantId = restaurantService.createRestaurant(req);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.onSuccess(restaurantId));
    }

    /**
     * API 2: GET /restaurants — 식당 목록 조회 (페이징)
     */
    @Operation(summary = "식당 목록 조회", description = "활성 상태의 식당 목록을 페이징으로 조회합니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "잘못된 페이징 요청"),
    })
    @GetMapping
    public ResponseEntity<ApiResponse<Page<RestaurantRes>>> getRestaurants(
            @Parameter(description = "페이지 번호 (0부터 시작)", example = "0")
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 크기", example = "20")
            @RequestParam(defaultValue = "20") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<RestaurantRes> restaurants = restaurantService.getRestaurants(pageable);
        return ResponseEntity.ok(ApiResponse.onSuccess(restaurants));
    }

    /**
     * API 3: GET /restaurants/{restaurantId} — 식당 상세 조회
     */
    @Operation(summary = "식당 상세 조회", description = "특정 식당의 상세 정보를 조회합니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "식당을 찾을 수 없음"),
    })
    @GetMapping("/{restaurantId}")
    public ResponseEntity<ApiResponse<RestaurantRes>> getRestaurantById(
            @Parameter(description = "식당 ID", example = "1")
            @PathVariable Long restaurantId) {

        RestaurantRes restaurant = restaurantService.getRestaurantById(restaurantId);
        return ResponseEntity.ok(ApiResponse.onSuccess(restaurant));
    }

    /**
     * API 4: PUT /restaurants/{restaurantId} — 식당 정보 수정
     */
    @Operation(summary = "식당 정보 수정", description = "식당의 정보를 수정합니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "수정 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "식당을 찾을 수 없음"),
    })
    @PutMapping("/{restaurantId}")
    public ResponseEntity<ApiResponse<RestaurantRes>> updateRestaurant(
            @Parameter(description = "식당 ID", example = "1")
            @PathVariable Long restaurantId,
            @Valid @RequestBody RestaurantReq req) {

        RestaurantRes restaurant = restaurantService.updateRestaurant(restaurantId, req);
        return ResponseEntity.ok(ApiResponse.onSuccess(restaurant));
    }

    /**
     * API 5: DELETE /restaurants/{restaurantId} — 식당 삭제
     */
    @Operation(summary = "식당 삭제", description = "식당을 삭제합니다.")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "삭제 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "식당을 찾을 수 없음"),
    })
    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<ApiResponse<Void>> deleteRestaurant(
            @Parameter(description = "식당 ID", example = "1")
            @PathVariable Long restaurantId) {

        restaurantService.deleteRestaurant(restaurantId);
        return ResponseEntity.ok(ApiResponse.onSuccess(null));
    }
}
