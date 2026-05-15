package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.order.OrderItemOption;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderItemOptionRes {

    private Long id;
    private String optionGroup;
    private String optionName;
    private Integer additionalPrice;

    public static OrderItemOptionRes from(OrderItemOption option) {
        return OrderItemOptionRes.builder()
                .id(option.getId())
                .optionGroup(option.getOptionGroup())
                .optionName(option.getOptionName())
                .additionalPrice(option.getAdditionalPrice())
                .build();
    }
}
