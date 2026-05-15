package com.kuit.baemin.dto.response;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class OrderListRes {
    private List<OrderDetailRes> orders;
    private int currentPage;
}