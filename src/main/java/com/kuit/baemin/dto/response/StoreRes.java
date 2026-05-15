package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoreRes {

    private Long storeId;
    private String name;
    private String category;
    private String address;
    private String storePictureUrl;
    private Integer minDeliveryPrice;
    private Integer deliveryTip;
    private Integer minDeliveryTime;
    private Integer maxDeliveryTime;
    private Double rating;
    private Integer reviewCount;

    public static StoreRes from(Restaurant restaurant) {
        return StoreRes.builder()
                .storeId(restaurant.getId())
                .name(restaurant.getName())
                .category(restaurant.getCategory())
                .address(restaurant.getAddress())
                .storePictureUrl(restaurant.getStorePictureUrl())
                .minDeliveryPrice(restaurant.getMinDeliveryPrice())
                .deliveryTip(restaurant.getDeliveryTip())
                .minDeliveryTime(restaurant.getMinDeliveryTime())
                .maxDeliveryTime(restaurant.getMaxDeliveryTime())
                .rating(restaurant.getRating())
                .reviewCount(restaurant.getReviewCount())
                .build();
    }
}
