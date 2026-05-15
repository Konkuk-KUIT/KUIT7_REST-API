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
    EMPTY_UPDATE_VALUE(HttpStatus.BAD_REQUEST, "MEMBER400", "수정할 회원 정보가 없습니다."),
    MEMBER_ALREADY_DELETED(HttpStatus.CONFLICT, "MEMBER409", "이미 탈퇴한 회원입니다."),

    // 주소
    INVALID_ADDRESS(HttpStatus.BAD_REQUEST, "ADDRESS400", "주소는 필수 입력값입니다."),
    ADDRESS_NOT_FOUND(HttpStatus.NOT_FOUND, "ADDRESS404", "주소를 찾을 수 없습니다."),
    ADDRESS_EMPTY_UPDATE_VALUE(HttpStatus.BAD_REQUEST, "ADDRESS400", "수정할 주소 정보가 없습니다."),
    ADDRESS_ALREADY_DELETED(HttpStatus.CONFLICT, "ADDRESS409", "이미 삭제된 주소입니다."),
    ADDRESS_ALREADY_DEFAULT(HttpStatus.CONFLICT, "ADDRESS409", "이미 기본 주소로 설정된 주소입니다."),

    //가게
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE404", "가게를 찾을 수 없습니다."),
    STORE_NOT_AVAILABLE(HttpStatus.CONFLICT, "STORE409", "현재 이용할 수 없는 가게입니다."),
    ORDER_MIN_PRICE_NOT_MET(HttpStatus.BAD_REQUEST, "ORDER400", "최소 주문 금액을 충족하지 못했습니다."),
    // ── TODO: 미션에서 필요한 에러 코드 추가 ──
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
