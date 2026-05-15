package com.kuit.baemin.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;

@Getter
public class OrderCreateReq {

    @NotNull(message = "회원 ID는 필수입니다.")
    private Long memberId;

    @NotNull(message = "가게 ID는 필수입니다.")
    private Long restaurantId;

    @NotNull(message = "주소 ID는 필수입니다.")
    private Long addressId;

    @Size(max = 500, message = "요청사항은 500자 이하여야 합니다.")
    private String requestMessage;

    @NotNull(message = "배달팁은 필수입니다.")
    @DecimalMin(value = "0.00", message = "배달팁은 0원 이상이어야 합니다.")
    private BigDecimal deliveryFee;

    @Size(max = 100, message = "배달 도로명 주소는 100자 이하여야 합니다.")
    private String deliveryRoadAddress;

    @Size(max = 100, message = "배달 상세 주소는 100자 이하여야 합니다.")
    private String deliveryDetailAddress;

    @Valid
    @NotEmpty(message = "주문 메뉴는 필수입니다.")
    private List<OrderItemReq> orderItems;

    @Getter
    public static class OrderItemReq {

        @NotNull(message = "메뉴 ID는 필수입니다.")
        private Long menuId;

        @NotBlank(message = "메뉴명은 필수입니다.")
        @Size(max = 100, message = "메뉴명은 100자 이하여야 합니다.")
        private String menuName;

        @NotNull(message = "메뉴 단가는 필수입니다.")
        @DecimalMin(value = "0.00", message = "메뉴 단가는 0원 이상이어야 합니다.")
        private BigDecimal unitPrice;

        @NotNull(message = "수량은 필수입니다.")
        @Positive(message = "수량은 1개 이상이어야 합니다.")
        private Integer quantity;

        @Valid
        private List<OrderItemOptionReq> options;
    }

    @Getter
    public static class OrderItemOptionReq {

        private Long menuOptionId;

        @NotBlank(message = "옵션 그룹명은 필수입니다.")
        @Size(max = 100, message = "옵션 그룹명은 100자 이하여야 합니다.")
        private String optionGroupName;

        @NotBlank(message = "옵션명은 필수입니다.")
        @Size(max = 100, message = "옵션명은 100자 이하여야 합니다.")
        private String optionName;

        @NotNull(message = "추가 금액은 필수입니다.")
        @DecimalMin(value = "0.00", message = "추가 금액은 0원 이상이어야 합니다.")
        private BigDecimal additionalPrice;
    }
}
