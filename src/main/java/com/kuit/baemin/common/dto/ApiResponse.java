package com.kuit.baemin.common.dto;

import static com.kuit.baemin.common.dto.SuccessStatus.API_SUCCESS;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    private final Boolean isSuccess;
    private final String code;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    // ── 성공 응답 ──
    public static <T> ApiResponse<T> onSuccess(T result) {
        return new ApiResponse<>(true, API_SUCCESS.getCode(), API_SUCCESS.getMessage(), result);
    }

    public static <T> ApiResponse<T> of(T result) {
        return new ApiResponse<>(true, API_SUCCESS.getCode(), API_SUCCESS.getMessage(), result);
    }

    // ── 실패 응답 ──
    public static <T> ApiResponse<T> onFailure(String code, String message, T data) {
        return new ApiResponse<>(false, code, message, data);
    }
}
