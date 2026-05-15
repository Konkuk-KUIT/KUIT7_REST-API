package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.MemberStatusReq;
import com.kuit.baemin.dto.request.SignUpReq;
import com.kuit.baemin.dto.response.MemberRes;
import com.kuit.baemin.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Member", description = "회원 API")
@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원가입", description = "이메일, 닉네임, 전화번호로 회원가입합니다.")
    @PostMapping
    public ApiResponse<Long> signUp(@Valid @RequestBody SignUpReq req) {
        return ApiResponse.of(memberService.signUp(req));
    }

    @Operation(summary = "회원 조회", description = "회원 ID로 프로필을 조회합니다.")
    @GetMapping("/{memberId}")
    public ApiResponse<MemberRes> getMember(
            @Parameter(description = "회원 ID", example = "1") @PathVariable Long memberId) {
        return ApiResponse.of(memberService.getMember(memberId));
    }

    @Operation(summary = "회원 상태 변경", description = "회원 상태를 변경합니다. (LEAVE: 탈퇴, BLOCK: 차단)")
    @PatchMapping("/{memberId}/status")
    public ApiResponse<Long> updateStatus(
            @Parameter(description = "회원 ID", example = "1") @PathVariable Long memberId,
            @Valid @RequestBody MemberStatusReq req) {
        return ApiResponse.of(memberService.updateStatus(memberId, req));
    }
}
