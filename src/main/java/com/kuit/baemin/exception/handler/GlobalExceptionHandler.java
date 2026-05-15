package com.kuit.baemin.exception.handler;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.exception.GeneralException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @Valid 검증 실패 — 400
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        String message = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        log.warn("[Validation 오류] {}", message);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.onFailure(ErrorStatus.BAD_REQUEST.getCode(), message, null));
    }

    /**
     * JSON 파싱 실패 (잘못된 형식, 잘못된 enum 값 등) — 400
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<?>> handleHttpMessageNotReadable(HttpMessageNotReadableException e) {
        log.warn("[JSON 파싱 오류] {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponse.onFailure(ErrorStatus.BAD_REQUEST.getCode(), "요청 본문을 읽을 수 없습니다. JSON 형식 또는 값을 확인해주세요.", null));
    }

    /**
     * 허용되지 않는 HTTP 메서드 — 405
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<?>> handleMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        log.warn("[메서드 미지원] {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(ApiResponse.onFailure("COMMON405", "지원하지 않는 HTTP 메서드입니다.", null));
    }

    /**
     * 존재하지 않는 URL — 404
     */
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleNoResourceFound(NoResourceFoundException e) {
        log.warn("[URL 없음] {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.onFailure(ErrorStatus.NOT_FOUND.getCode(), ErrorStatus.NOT_FOUND.getMessage(), null));
    }

    /**
     * 커스텀 비즈니스 예외 (GeneralException 및 하위 예외 포함) — ErrorStatus 기반
     */
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ApiResponse<?>> handleGeneralException(GeneralException e) {
        ErrorStatus status = e.getErrorStatus();
        log.warn("[비즈니스 예외] code={}, message={}", status.getCode(), status.getMessage());
        return ResponseEntity
                .status(status.getHttpStatus())
                .body(ApiResponse.onFailure(status.getCode(), status.getMessage(), null));
    }

    /**
     * 그 외 예상치 못한 예외 — 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception e) {
        log.error("[서버 오류] 처리되지 않은 예외 발생", e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.onFailure(
                        ErrorStatus.INTERNAL_SERVER_ERROR.getCode(),
                        ErrorStatus.INTERNAL_SERVER_ERROR.getMessage(),
                        null));
    }
}
