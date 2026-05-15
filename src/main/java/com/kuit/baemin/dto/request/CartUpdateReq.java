package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CartUpdateReq {

    @Size(max = 200, message = "요청사항은 200자 이내로 입력해주세요.")
    private String request;

    @Size(max = 200, message = "가게 요청사항은 200자 이내로 입력해주세요.")
    private String storeRequest;
}
