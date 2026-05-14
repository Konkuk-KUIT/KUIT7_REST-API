package com.kuit.baemin.service;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.dto.response.RestaurantRes;
import com.kuit.baemin.exception.OrderException;
import com.kuit.baemin.exception.RestaurantException;
import com.kuit.baemin.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

import static com.kuit.baemin.exception.errorcode.ErrorStatus.RESTAURANT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    /**
     * 음식점 목록 조회
     */
    public List<RestaurantRes> getRestaurants(String address, Integer minOrderPrice) {
        List<Restaurant> restaurants;

        // 1. 둘 다 검색 조건이 있는 경우
        if (address != null && minOrderPrice != null) {
            restaurants = restaurantRepository.findByAddressContainingAndMinOrderPriceLessThanEqual(address, minOrderPrice);
        }
        // 2. 주소로만 검색하는 경우
        else if (address != null) {
            restaurants = restaurantRepository.findByAddressContaining(address);
        }
        // 3. 가격으로만 검색하는 경우
        else if (minOrderPrice != null) {
            restaurants = restaurantRepository.findByMinOrderPriceLessThanEqual(minOrderPrice);
        }
        // 4. 조건이 없으면 전체 조회
        else {
            restaurants = restaurantRepository.findAll();
        }

        if (restaurants.isEmpty()) {
            throw new RestaurantException(RESTAURANT_NOT_FOUND);
        }

        // 엔티티 리스트를 응답 DTO 리스트로 변환해서 반환
        return restaurants.stream()
                .map(RestaurantRes::from)
                .collect(Collectors.toList());
    }
}
