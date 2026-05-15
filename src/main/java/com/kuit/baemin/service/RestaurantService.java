package com.kuit.baemin.service;

import com.kuit.baemin.domain.Restaurant.Category;
import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.Restaurant.RestaurantStatus;
import com.kuit.baemin.dto.request.RestaurantReq;
import com.kuit.baemin.dto.response.RestaurantRes;
import com.kuit.baemin.exception.GeneralException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.CategoryRepository;
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
    private final CategoryRepository categoryRepository;

    // API 4: 식당 생성
    @Transactional
    public Long createRestaurant(RestaurantReq req) {
        Category category = categoryRepository.findById(req.getCategoryId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.CATEGORY_NOT_FOUND));

        Restaurant restaurant = Restaurant.builder()
                .category(category)
                .name(req.getName())
                .address(req.getAddress())
                .lat(req.getLat())
                .lng(req.getLng())
                .minOrderPrice(req.getMinOrderPrice())
                .deliveryFee(req.getDeliveryFee())
                .status(RestaurantStatus.ACTIVE)
                .build();

        return restaurantRepository.save(restaurant).getId();
    }

    // API 5: 식당 목록 조회
    public Page<RestaurantRes> getRestaurants(Pageable pageable) {
        return restaurantRepository.findByStatus(RestaurantStatus.ACTIVE, pageable)
                .map(RestaurantRes::from);
    }

    // API 6: 식당 상세 조회
    public RestaurantRes getRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND));
        return RestaurantRes.from(restaurant);
    }

    // API 7: 식당 삭제
    @Transactional
    public void deleteRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND));
        restaurantRepository.delete(restaurant);
    }

    // API 11: 식당 정보 수정
    @Transactional
    public RestaurantRes updateRestaurant(Long restaurantId, RestaurantReq req) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND));

        Category category = categoryRepository.findById(req.getCategoryId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.CATEGORY_NOT_FOUND));

        Restaurant updated = Restaurant.builder()
                .id(restaurant.getId())
                .category(category)
                .name(req.getName())
                .address(req.getAddress())
                .lat(req.getLat())
                .lng(req.getLng())
                .minOrderPrice(req.getMinOrderPrice())
                .deliveryFee(req.getDeliveryFee())
                .status(restaurant.getStatus())
                .build();

        return RestaurantRes.from(restaurantRepository.save(updated));
    }
}