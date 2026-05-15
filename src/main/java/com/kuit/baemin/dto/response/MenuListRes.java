package com.kuit.baemin.dto.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Builder
public class MenuListRes {

    private List<MenuRes> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean last;

    public static MenuListRes from(Page<MenuRes> menus) {
        return MenuListRes.builder()
                .content(menus.getContent())
                .page(menus.getNumber())
                .size(menus.getSize())
                .totalElements(menus.getTotalElements())
                .totalPages(menus.getTotalPages())
                .last(menus.isLast())
                .build();
    }
}
