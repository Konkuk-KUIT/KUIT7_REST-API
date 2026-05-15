package com.kuit.baemin.service;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.Restaurant.RestaurantStatus;
import com.kuit.baemin.dto.request.RestaurantReq;
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

    /**
     * API 1: POST /restaurants — 식당 생성
     */
    @Transactional
    public Long createRestaurant(RestaurantReq req) {
        Restaurant restaurant = Restaurant.builder()
                .name(req.getName())
                .phoneNumber(req.getPhoneNumber())
                .roadAddress(req.getRoadAddress())
                .detailAddress(req.getDetailAddress())
                .latitude(req.getLatitude())
                .longitude(req.getLongitude())
                .minOrderAmount(req.getMinOrderAmount())
                .deliveryFee(req.getDeliveryFee())
                .status(RestaurantStatus.ACTIVE)
                .build();

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return savedRestaurant.getId();
    }

    /**
     * API 2: GET /restaurants — 식당 목록 조회 (페이징)
     */
    public Page<RestaurantRes> getRestaurants(Pageable pageable) {
        Page<Restaurant> restaurants = restaurantRepository.findByStatus("ACTIVE", pageable);
        return restaurants.map(RestaurantRes::from);
    }

    /**
     * API 3: GET /restaurants/{restaurantId} — 식당 상세 조회
     */
    public RestaurantRes getRestaurantById(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND));
        return RestaurantRes.from(restaurant);
    }

    /**
     * API 4: PUT /restaurants/{restaurantId} — 식당 정보 수정
     */
    @Transactional
    public RestaurantRes updateRestaurant(Long restaurantId, RestaurantReq req) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND));

        Restaurant updated = Restaurant.builder()
                .id(restaurant.getId())
                .name(req.getName())
                .phoneNumber(req.getPhoneNumber())
                .roadAddress(req.getRoadAddress())
                .detailAddress(req.getDetailAddress())
                .latitude(req.getLatitude())
                .longitude(req.getLongitude())
                .minOrderAmount(req.getMinOrderAmount())
                .deliveryFee(req.getDeliveryFee())
                .status(restaurant.getStatus())
                .menus(restaurant.getMenus())
                .orders(restaurant.getOrders())
                .build();

        restaurantRepository.save(updated);
        return RestaurantRes.from(updated);
    }

    /**
     * API 5: DELETE /restaurants/{restaurantId} — 식당 삭제
     */
    @Transactional
    public void deleteRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND));
        restaurantRepository.delete(restaurant);
    }
}
