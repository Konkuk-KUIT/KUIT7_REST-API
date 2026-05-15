package com.kuit.baemin.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateOrderReq {
    @NotNull(message = "사용자 ID는 필수입니다.")
    private Long userId;

    @NotNull(message = "가게 ID는 필수입니다.")
    private Long storeId;

    @NotNull(message = "주소 ID는 필수입니다.")
    private Long addressId;

    @Size(max = 255, message = "요청사항은 255자 이하여야 합니다.")
    @JsonProperty("request_message")
    private String requestMessage;
}
