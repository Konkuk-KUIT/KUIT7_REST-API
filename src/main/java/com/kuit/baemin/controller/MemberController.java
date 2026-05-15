package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.LoginReq;
import com.kuit.baemin.dto.request.SignUpReq;
import com.kuit.baemin.dto.response.MemberRes;
import com.kuit.baemin.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
 *    - 토큰에서 사용자 정보(memberId 등) 추출
 *    - 현재는 PathVariable이나 RequestBody로 memberId를 직접 받고 있음
 *
 * 3. 인가 (Authorization)
 *    - 요청한 사용자가 해당 리소스에 접근 권한이 있는지 확인
 *    - ex) 본인 주문만 조회 가능, 본인 리뷰만 수정 가능
 *
 *  8주차 커리큘럼(인증, 인가, JWT)을 학습한 후 직접 추가해보세요!
 */
@Tag(name = "Member", description = "회원 관련 API")
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원 가입")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "회원 가입 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "409", description = "이메일 중복")
    })
    @PostMapping
    public ApiResponse<Long> signUp(@Valid @RequestBody SignUpReq req) {
        return ApiResponse.of(memberService.signUp(req));
    }

    @Operation(summary = "로그인")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "로그인 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "비밀번호 불일치"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "존재하지 않는 회원")
    })
    @PostMapping("/login")
    public ApiResponse<Long> login(@Valid @RequestBody LoginReq req) {
        return ApiResponse.of(memberService.login(req));
    }

    @Operation(summary = "회원 단건 조회")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공")
    @GetMapping("/{memberId}")
    public ApiResponse<MemberRes> getMember(
            @Parameter(description = "회원 ID") @PathVariable Long memberId) {
        return ApiResponse.of(memberService.getMember(memberId));
    }
}

