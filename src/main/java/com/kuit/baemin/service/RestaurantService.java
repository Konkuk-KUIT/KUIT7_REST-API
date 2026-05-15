package com.kuit.baemin.service;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.Restaurant.RestaurantStatus;
import com.kuit.baemin.dto.response.RestaurantRes;
import com.kuit.baemin.exception.GeneralException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public Page<RestaurantRes> getRestaurants(String category, Pageable pageable) {
        Page<Restaurant> restaurants;

        if (category == null || category.isBlank()) {
            restaurants = restaurantRepository.findByStatus(RestaurantStatus.ACTIVE, pageable);
        } else {
            restaurants = restaurantRepository.findByCategoryAndStatus(category, RestaurantStatus.ACTIVE, pageable);
        }

        return restaurants.map(RestaurantRes::from);
    }

    public RestaurantRes getRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND));

        return RestaurantRes.from(restaurant);
    }
}
