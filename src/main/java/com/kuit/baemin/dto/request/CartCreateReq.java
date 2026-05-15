package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CartCreateReq {
    @NotNull(message = "사용자 ID는 필수입니다.")
    private Long userId;

    @NotNull(message = "가게 ID는 필수입니다.")
    private Long storeId;

    @NotNull(message = "메뉴 ID는 필수입니다.")
    private Long menuId;

    @NotNull(message = "메뉴 상세 옵션 ID는 필수입니다.")
    private Long menuDetailId;

    @NotBlank(message = "결제 수단을 선택해주세요.")
    private String payMethod;

    @Size(max = 200, message = "요청사항은 200자 이내로 입력해주세요.")
    private String request;

    @Size(max = 200, message = "가게 요청사항은 200자 이내로 입력해주세요.")
    private String storeRequest;
}
