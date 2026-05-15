package com.kuit.baemin.service;

import static com.kuit.baemin.exception.errorcode.ErrorStatus.RESTAURANT_NOT_FOUND;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.Restaurant.RestaurantStatus;
import com.kuit.baemin.dto.request.RestaurantCreateReq;
import com.kuit.baemin.dto.response.RestaurantRes;
import com.kuit.baemin.exception.RestaurantException;
import com.kuit.baemin.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Transactional
    public RestaurantRes createRestaurant(RestaurantCreateReq req) {
        Restaurant restaurant = Restaurant.builder()
                .name(req.getName())
                .description(req.getDescription())
                .phoneNumber(req.getPhoneNumber())
                .roadAddress(req.getRoadAddress())
                .detailAddress(req.getDetailAddress())
                .latitude(req.getLatitude())
                .longitude(req.getLongitude())
                .minOrderAmount(req.getMinOrderAmount())
                .deliveryFee(req.getDeliveryFee())
                .imageUrl(req.getImageUrl())
                .status(RestaurantStatus.active)
                .build();

        return RestaurantRes.from(restaurantRepository.save(restaurant));
    }

    @Transactional
    public Long deleteRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantException(RESTAURANT_NOT_FOUND));
        restaurant.delete();
        return restaurant.getId();
    }
}
