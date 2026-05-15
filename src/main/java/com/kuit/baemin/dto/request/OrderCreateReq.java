package com.kuit.baemin.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderCreateReq {

    @NotNull(message = "가게 ID는 필수입니다.")
    private Long storeId;

    @NotNull(message = "사용자 ID는 필수입니다.")
    private Long userId;

    @NotBlank(message = "결제 방식을 선택해주세요.")
    private String payMethod;

    @Size(max = 200, message = "배달 요청사항을 입력해주세요.")
    private String request;

    @Size(max = 200, message = "가게 요청사항은 200자 이내로 입력해주세요.")
    private String storeRequest;

    @Valid
    @NotEmpty(message = "주문할 메뉴를 최소 하나 이상 선택해주세요.")
    private List<OrderMenuReq> orderMenus;
}
