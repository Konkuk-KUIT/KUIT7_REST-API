package com.kuit.baemin.exception.errorcode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus {

    // ── 공통 ──
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "권한이 없습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404", "요청한 리소스를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 오류가 발생했습니다."),

    // ── 회원 ──
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404", "존재하지 않는 회원입니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "MEMBER409", "이미 사용 중인 이메일입니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "MEMBER401", "비밀번호가 일치하지 않습니다."),

    // ── 주소 ──
    ADDRESS_NOT_FOUND(HttpStatus.NOT_FOUND, "ADDRESS404", "존재하지 않는 주소입니다."),

    // ── 카테고리 ──
    CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "CATEGORY404", "존재하지 않는 카테고리입니다."),

    // ── 식당 ──
    RESTAURANT_NOT_FOUND(HttpStatus.NOT_FOUND, "RESTAURANT404", "존재하지 않는 식당입니다."),

    // ── 메뉴 ──
    MENU_NOT_FOUND(HttpStatus.NOT_FOUND, "MENU404", "존재하지 않는 메뉴입니다."),

    // ── 주문 ──
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "ORDER404", "존재하지 않는 주문입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}