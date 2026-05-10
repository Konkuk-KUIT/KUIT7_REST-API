package com.kuit.baemin.repository;

import com.kuit.baemin.domain.favorite.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
  List<Favorite> findByMemberId(Long memberId);
  boolean existsByMemberIdAndRestaurantId(Long memberId, Long restaurantId);
}
