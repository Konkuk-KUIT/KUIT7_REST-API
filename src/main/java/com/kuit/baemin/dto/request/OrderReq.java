package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class OrderReq {

    @NotNull(message = "주문하는 사용자 ID는 필수입니다.") // 숫자형 필수값 체크
    private Long memberId;

    @NotNull(message = "주문할 식당 ID는 필수입니다.")
    private Long restaurantId;

    @NotEmpty(message = "주문할 메뉴를 최소 하나 이상 선택해주세요.") // 리스트가 비어있는지 체크
    private List<OrderMenuReq> orderMenus;

    @Size(max = 100, message = "요청사항은 100자 이하로 작성해주세요.") // 선택 사항이지만 글자수 제한
    private String request;

    @NotNull(message = "최종 결제 금액은 필수입니다.")
    private Integer totalPrice; // int 대신 래퍼 클래스인 Integer를 써야 @NotNull이 먹힙니다!
}
