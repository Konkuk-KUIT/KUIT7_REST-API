package com.kuit.baemin.dto.response;


import com.kuit.baemin.domain.Store.Store;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoreRes {

    // 자바 명명 규칙에 맞게 카멜 케이스로 모두 수정
    private Long storeId;
    private String storeName;
    private String storeAddress;
    private String category;
    private String storeNumber;

    // Store 엔티티 -> DTO 변환
    public static StoreRes from(Store store){
        return StoreRes.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .storeAddress(store.getAddress())
                .category(store.getCategory())
                .storeNumber(store.getStoreNumber())
                .build();
    }
}
