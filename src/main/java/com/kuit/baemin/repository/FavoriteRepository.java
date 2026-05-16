package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
  List<Favorite> findByUserId(Long userId);
  boolean existsByUserIdAndStoreId(Long userId, Long storeId);
}
