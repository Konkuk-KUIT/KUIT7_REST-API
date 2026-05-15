package com.kuit.baemin.repository;

import com.kuit.baemin.domain.category.Category;
import com.kuit.baemin.domain.category.CategoryStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByName(String name);

    Page<Category> findAllByStatus(CategoryStatus status, Pageable pageable);
}
