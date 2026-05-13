package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.LoginReq;
import com.kuit.baemin.dto.request.SignUpReq;
import com.kuit.baemin.dto.request.UserUpdateReq;
import com.kuit.baemin.dto.response.UserRes;
import com.kuit.baemin.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * TODO: 인증/인가 처리 미구현 (8주차에서 다룰 예정)
 *
 * 현재 이 컨트롤러는 JWT 토큰 검증 및 인증/인가 로직이 생략되어 있습니다.
 * 실제 서비스라면 아래 항목들이 반드시 구현되어야 합니다:
 *
 * 1. JWT 토큰 검증
 *    - 요청 헤더(Authorization: Bearer {token})에서 토큰 추출
 *    - 토큰 유효성 검증 (만료 여부, 서명 검증 등)
 *
 * 2. 인증 (Authentication)
 *    - 토큰에서 사용자 정보(userId 등) 추출
 *    - 현재는 PathVariable이나 RequestBody로 userId를 직접 받고 있음
 *
 * 3. 인가 (Authorization)
 *    - 요청한 사용자가 해당 리소스에 접근 권한이 있는지 확인
 *    - ex) 본인 주문만 조회 가능, 본인 리뷰만 수정 가능
 *
 *  8주차 커리큘럼(인증, 인가, JWT)을 학습한 후 직접 추가해보세요!
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * POST /users — 회원 가입
     */
    @PostMapping
    @Operation(summary = "회원 가입")
    public ApiResponse<Long> signUp(@Valid @RequestBody SignUpReq req) {
        return ApiResponse.of(userService.signUp(req));
    }

    /**
     * POST /users/login — 로그인
     */
    @PostMapping("/login")
    @Operation(summary = "로그인")
    public ApiResponse<Long> login(@Valid @RequestBody LoginReq req) {
        return ApiResponse.of(userService.login(req));
    }

    /**
     * GET /users/{userId} — 회원 단건 조회
     */
    @GetMapping("/{userId}")
    @Operation(summary = "회원 단건 조회")
    public ApiResponse<UserRes> getUser(@PathVariable Long userId) {
        return ApiResponse.of(userService.getUser(userId));
    }

    /**
     * DELETE /users/{userId} - 회원 탈퇴
     */
    @DeleteMapping("/{userId}")
    @Operation(summary = "회원 탈퇴")
    public ApiResponse<Void> deleteUser(@PathVariable Long userId) {

        userService.deleteUser(userId);
        return ApiResponse.onSuccess(null);
    }

    /**
     * PATCH /users/{userId} - 회원 정보 수정
     */
    @PatchMapping("/{userId}")
    @Operation(summary = "회원 정보 수정")
    public ApiResponse<Void> updateUser(@Valid @RequestBody UserUpdateReq req, @PathVariable Long userId) {

        userService.updateUser(userId, req);
        return ApiResponse.onSuccess(null);
    }
}
