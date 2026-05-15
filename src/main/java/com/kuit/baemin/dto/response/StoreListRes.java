package com.kuit.baemin.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Builder
public class StoreListRes {

    private List<StoreRes> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;

    public static StoreListRes from(Page<StoreRes> stores) {
        return StoreListRes.builder()
                .content(stores.getContent())
                .page(stores.getNumber())
                .size(stores.getSize())
                .totalElements(stores.getTotalElements())
                .totalPages(stores.getTotalPages())
                .last(stores.isLast())
                .build();
    }
}
