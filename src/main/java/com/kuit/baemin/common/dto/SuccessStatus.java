package com.kuit.baemin.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    // 공통
    API_SUCCESS(true, "20000", "요청에 성공했습니다."),
    ;

    private final boolean isSuccess;
    private final String code;
    private final String message;
}
