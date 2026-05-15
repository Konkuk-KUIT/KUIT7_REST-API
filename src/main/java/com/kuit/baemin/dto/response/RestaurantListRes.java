package com.kuit.baemin.dto.response;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class RestaurantListRes {
    private List<RestaurantDetailRes> restaurants;
    private int totalPage;
    private long totalElements;
    private boolean isFirst;
    private boolean isLast;
}