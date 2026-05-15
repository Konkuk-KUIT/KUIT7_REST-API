package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.CategoryCreateReq;
import com.kuit.baemin.dto.response.CategoryRes;
import com.kuit.baemin.dto.response.PageRes;
import com.kuit.baemin.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ApiResponse<Long> create(@Valid @RequestBody CategoryCreateReq req) {
        return ApiResponse.of(categoryService.create(req));
    }

    @GetMapping
    public ApiResponse<PageRes<CategoryRes>> list(
            @PageableDefault(size = 20, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return ApiResponse.of(categoryService.list(pageable));
    }
}
