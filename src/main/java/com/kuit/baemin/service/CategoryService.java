package com.kuit.baemin.service;

import com.kuit.baemin.domain.category.Category;
import com.kuit.baemin.domain.category.CategoryStatus;
import com.kuit.baemin.dto.request.CategoryCreateReq;
import com.kuit.baemin.dto.response.CategoryRes;
import com.kuit.baemin.dto.response.PageRes;
import com.kuit.baemin.exception.GeneralException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public Long create(CategoryCreateReq req) {
        if (categoryRepository.existsByName(req.getName())) {
            throw new GeneralException(ErrorStatus.DUPLICATE_CATEGORY_NAME);
        }

        Category category = Category.builder()
                .name(req.getName())
                .status(CategoryStatus.ACTIVE)
                .build();

        return categoryRepository.save(category).getId();
    }

    public PageRes<CategoryRes> list(Pageable pageable) {
        return PageRes.of(
                categoryRepository.findAllByStatus(CategoryStatus.ACTIVE, pageable),
                CategoryRes::from
        );
    }
}
