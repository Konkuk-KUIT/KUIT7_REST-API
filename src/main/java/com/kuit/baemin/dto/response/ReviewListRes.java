package com.kuit.baemin.dto.response;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Getter
@Builder
public class ReviewListRes {
    private List<ReviewDetailRes> reviews;
    private int totalPage;
    private long totalElements;
    private boolean isFirst;
    private boolean isLast;
}