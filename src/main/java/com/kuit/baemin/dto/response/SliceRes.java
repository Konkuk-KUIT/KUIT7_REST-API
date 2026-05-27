package com.kuit.baemin.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Slice;

import java.util.List;

@Getter
@Builder
public class SliceRes<T> {
    private List<T> content;
    private Integer currentPage;
    private Boolean hasNext;
    private Boolean isFirst;
    private Boolean isLast;

    public static <T> SliceRes<T> from(Slice<T> slice) {
        return SliceRes.<T>builder()
                .content(slice.getContent())
                .currentPage(slice.getNumber())
                .hasNext(slice.hasNext())
                .isFirst(slice.isFirst())
                .isLast(slice.isLast())
                .build();
    }
}
