package com.kuit.baemin.service;

import com.kuit.baemin.domain.restaurant.Restaurant;
import com.kuit.baemin.domain.restaurant.RestaurantStatus;
import com.kuit.baemin.dto.request.CreateRestaurantReq;
import com.kuit.baemin.dto.response.RestaurantRes;
import com.kuit.baemin.exception.RestaurantException;
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

    @Transactional
    public Long createRestaurant(CreateRestaurantReq req) {
        Restaurant restaurant = Restaurant.builder()
                .restaurantName(req.getRestaurantName())
                .category(req.getCategory())
                .address(req.getAddress())
                .minOrderAmount(req.getMinOrderAmount())
                .deliveryFee(req.getDeliveryFee())
                .rate(0.0f)
                .isOpen(true)
                .status(RestaurantStatus.ACTIVE)
                .build();
        return restaurantRepository.save(restaurant).getId();
    }

    public Page<RestaurantRes> getRestaurants(Pageable pageable) {
        return restaurantRepository.findAllByStatus(RestaurantStatus.ACTIVE, pageable)
                .map(RestaurantRes::from);
    }

    public RestaurantRes getRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantException(ErrorStatus.RESTAURANT_NOT_FOUND));
        return RestaurantRes.from(restaurant);
    }
}