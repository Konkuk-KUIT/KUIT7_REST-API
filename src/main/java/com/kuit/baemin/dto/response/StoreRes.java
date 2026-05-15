package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Store.Store;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoreRes {

    private Long storeId;
    private String storeName;
    private String storeCategory;
    private Integer deliveryMinimum;
    private String operatingTime;

    public static StoreRes from(Store store) {
        return new StoreRes(
                store.getStoreId(),
                store.getStoreName(),
                store.getStoreCategory(),
                store.getDeliveryMinimum(),
                store.getOperatingTime()
        );
    }
}
