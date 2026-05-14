package com.kuit.baemin.repository;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    // 주소에 특정 단어가 포함되고, 최소주문금액이 특정 금액 이하인 식당들 조회
    List<Restaurant> findByAddressContainingAndMinOrderPriceLessThanEqual(String address, Integer minOrderPrice);

    // 주소 검색만 할 경우
    List<Restaurant> findByAddressContaining(String address);

    // 최소주문금액 필터만 쓸 경우
    List<Restaurant> findByMinOrderPriceLessThanEqual(Integer minOrderPrice);
}