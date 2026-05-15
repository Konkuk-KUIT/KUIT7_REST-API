package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoreDetailRes {

    private Long storeId;
    private String name;
    private Integer type;
    private String category;
    private String address;
    private String storePictureUrl;
    private String phone;
    private String content;
    private Integer minDeliveryPrice;
    private Integer deliveryTip;
    private Integer minDeliveryTime;
    private Integer maxDeliveryTime;
    private Double rating;
    private Integer dibsCount;
    private Integer reviewCount;
    private String operationHours;
    private String closedDays;
    private String deliveryAddress;

    public static StoreDetailRes from(Restaurant restaurant) {
        return StoreDetailRes.builder()
                .storeId(restaurant.getId())
                .name(restaurant.getName())
                .type(restaurant.getType())
                .category(restaurant.getCategory())
                .address(restaurant.getAddress())
                .storePictureUrl(restaurant.getStorePictureUrl())
                .phone(restaurant.getPhone())
                .content(restaurant.getContent())
                .minDeliveryPrice(restaurant.getMinDeliveryPrice())
                .deliveryTip(restaurant.getDeliveryTip())
                .minDeliveryTime(restaurant.getMinDeliveryTime())
                .maxDeliveryTime(restaurant.getMaxDeliveryTime())
                .rating(restaurant.getRating())
                .dibsCount(restaurant.getDibsCount())
                .reviewCount(restaurant.getReviewCount())
                .operationHours(restaurant.getOperationHours())
                .closedDays(restaurant.getClosedDays())
                .deliveryAddress(restaurant.getDeliveryAddress())
                .build();
    }
}
