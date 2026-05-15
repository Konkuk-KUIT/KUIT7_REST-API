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
    ADDRESS_NOT_FOUND(HttpStatus.NOT_FOUND, "ADDRESS404", "존재하지 않는 배송지입니다."),

    // ── 가게 ──
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404", "존재하지 않는 가게입니다."),

    // ── 메뉴 ──
    MENU_NOT_FOUND(HttpStatus.NOT_FOUND, "MENU404", "존재하지 않는 메뉴입니다."),
    MENU_NOT_AVAILABLE(HttpStatus.BAD_REQUEST, "MENU400", "주문 불가능한 메뉴입니다."),

    // ── 주문 ──
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "ORDER404", "존재하지 않는 주문입니다."),
    ORDER_AMOUNT_TOO_LOW(HttpStatus.BAD_REQUEST, "ORDER400", "최소 주문 금액을 충족하지 못했습니다."),
    STORE_NOT_OPEN(HttpStatus.BAD_REQUEST, "ORDER401", "현재 주문 불가능한 가게입니다."),

    // ── 리뷰 ──
    REVIEW_ORDER_NOT_COMPLETED(HttpStatus.BAD_REQUEST, "REVIEW400", "배달 완료된 주문에만 리뷰를 작성할 수 있습니다."),
    REVIEW_ALREADY_EXISTS(HttpStatus.CONFLICT, "REVIEW409", "이미 리뷰가 작성된 주문입니다."),
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
