package com.kuit.baemin.exception.handler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.exception.GeneralException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @Valid 검증 실패 — 400
     */
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<?> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        String message = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        log.warn("[Validation 오류] {}", message);
        return ApiResponse.onFailure(ErrorStatus.BAD_REQUEST.getCode(), message, null);
    }

    /**
     * 커스텀 예외 GeneralException
     */
    /**
     * @Validated validation failure -> 400
     */
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResponse<?> handleConstraintViolation(ConstraintViolationException e) {
        String message = e.getConstraintViolations()
                .stream()
                .map(violation -> violation.getMessage())
                .collect(Collectors.joining(", "));
        log.warn("[Validation error] {}", message);
        return ApiResponse.onFailure(ErrorStatus.BAD_REQUEST.getCode(), message, null);
    }

    @ExceptionHandler(GeneralException.class)
    public ApiResponse<?> handleGeneralException(GeneralException e) {
        ErrorStatus status = e.getErrorStatus();
        log.warn("[GeneralException] code={}, message={}", status.getCode(), status.getMessage());
        return ApiResponse.onFailure(status.getCode(), status.getMessage(), null);
    }

    /**
     * 그 외 예상치 못한 예외 — 500
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleException(Exception e) {
        log.error("[Exception] 처리되지 않은 예외 발생", e);
        return ApiResponse.onFailure(
                ErrorStatus.INTERNAL_SERVER_ERROR.getCode(),
                ErrorStatus.INTERNAL_SERVER_ERROR.getMessage(),
                null
        );
    }
}
