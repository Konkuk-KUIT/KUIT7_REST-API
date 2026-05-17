package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Restaurant.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class StoreListRes {
    private Long storeId;
    private String name;
    private String category;
    private Integer deliveryTip;
    private BigDecimal rating;
    private Integer reviewCount;
    private Integer minDeliveryTime;
    private Integer maxDeliveryTime;

    public static StoreListRes from(Store store) {
        return StoreListRes.builder()
                .storeId(store.getId())
                .name(store.getName())
                .category(store.getCategory())
                .deliveryTip(store.getDeliveryTip())
                .rating(store.getRating())
                .reviewCount(store.getReviewCount())
                .minDeliveryTime(store.getMinDeliveryTime())
                .maxDeliveryTime(store.getMaxDeliveryTime())
                .build();
    }
}