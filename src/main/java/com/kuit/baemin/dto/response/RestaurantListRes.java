package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Builder
public class RestaurantListRes {
    private List<RestaurantRes> stores;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;

    public static RestaurantListRes from(Page<Restaurant> storePage){
        return RestaurantListRes.builder()
                .stores(
                        storePage.getContent()
                                .stream()
                                .map(RestaurantRes::from)
                                .toList()
                )
                .page(storePage.getNumber())
                .size(storePage.getSize())
                .totalElements(storePage.getTotalElements())
                .totalPages(storePage.getTotalPages())
                .build();

    }
}
