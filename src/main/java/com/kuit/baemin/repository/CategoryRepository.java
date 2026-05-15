package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Restaurant.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
