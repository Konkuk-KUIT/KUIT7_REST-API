package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Favorite;
import com.kuit.baemin.domain.Store.Store;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class FavoriteStoreRes {
    private Long storeId;
    private String storeName;
    private  String category;
    private int favoriteCount;
    private BigDecimal minPrice;


    //favorite에 있는 store 정보를 불러와서 DTO로 변환
    public static FavoriteStoreRes from(Favorite favorite){
        Store store = favorite.getStore();

        return FavoriteStoreRes.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .category(store.getCategory())
                .favoriteCount(store.getFavoriteCount())
                .minPrice(store.getMinPrice())
                .build();

    }


}
