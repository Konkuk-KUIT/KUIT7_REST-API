package com.kuit.baemin.exception.handler;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.exception.GeneralException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        String message = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        log.warn("[Validation Error] {}", message);
        return ResponseEntity
                .status(ErrorStatus.BAD_REQUEST.getHttpStatus())
                .body(ApiResponse.onFailure(ErrorStatus.BAD_REQUEST.getCode(), message, null));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<?>> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        log.warn("[HttpMessageNotReadableException] {}", e.getMessage());
        return ResponseEntity
                .status(ErrorStatus.BAD_REQUEST.getHttpStatus())
                .body(ApiResponse.onFailure(
                        ErrorStatus.BAD_REQUEST.getCode(),
                        "요청 본문 형식이 올바르지 않습니다.",
                        null
                ));
    }

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<?>> handleGeneralException(GeneralException e) {
        ErrorStatus status = e.getErrorStatus();
        log.warn("[GeneralException] code={}, message={}", status.getCode(), status.getMessage());
        return ResponseEntity
                .status(status.getHttpStatus())
                .body(ApiResponse.onFailure(status.getCode(), status.getMessage(), null));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleNoResourceFoundException(NoResourceFoundException e) {
        log.warn("[NoResourceFoundException] {}", e.getMessage());
        return ResponseEntity
                .status(ErrorStatus.NOT_FOUND.getHttpStatus())
                .body(ApiResponse.onFailure(
                        ErrorStatus.NOT_FOUND.getCode(),
                        ErrorStatus.NOT_FOUND.getMessage(),
                        null
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception e) {
        log.error("[Exception] Unexpected exception occurred.", e);
        return ResponseEntity
                .status(ErrorStatus.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(ApiResponse.onFailure(
                        ErrorStatus.INTERNAL_SERVER_ERROR.getCode(),
                        ErrorStatus.INTERNAL_SERVER_ERROR.getMessage(),
                        null
                ));
    }
}
