package com.kuit.baemin.service;

import com.kuit.baemin.domain.category.Category;

import com.kuit.baemin.domain.restaurant.Restaurant;
import com.kuit.baemin.domain.restaurant.RestaurantStatus;
import com.kuit.baemin.dto.request.RestaurantCreateReq;
import com.kuit.baemin.dto.response.RestaurantRes;
import com.kuit.baemin.repository.CategoryRepository;
import com.kuit.baemin.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final CategoryRepository categoryRepository;

    /**
     * 음식점 등록
     */
    @Transactional
    public Long createRestaurant(RestaurantCreateReq req) {

        Category category = categoryRepository.findById(req.getCategoryId())
                .orElseThrow(() ->
                        new IllegalArgumentException("카테고리를 찾을 수 없습니다.")
                );

        Restaurant restaurant = Restaurant.builder()
                .name(req.getName())
                .phoneNumber(req.getPhoneNumber())
                .roadAddress(req.getRoadAddress())
                .detailAddress(req.getDetailAddress())
                .latitude(req.getLatitude())
                .longitude(req.getLongitude())
                .minOrderPrice(req.getMinOrderPrice())
                .deliveryFee(req.getDeliveryFee())
                .imageUrl(req.getImageUrl())
                .status(RestaurantStatus.ACTIVE)
                .category(category)
                .build();

        return restaurantRepository.save(restaurant).getId();
    }

    /**
     * 음식점 전체 조회
     */
    public List<RestaurantRes> getRestaurants() {

        return restaurantRepository.findAll()
                .stream()
                .map(RestaurantRes::from)
                .toList();
    }

    /**
     * 음식점 단건 조회
     */
    public RestaurantRes getRestaurant(Long restaurantId) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() ->
                        new IllegalArgumentException("음식점을 찾을 수 없습니다.")
                );

        return RestaurantRes.from(restaurant);
    }

    /**
     * 카테고리별 음식점 조회
     */
    public List<RestaurantRes> getRestaurantsByCategory(Long categoryId) {

        return restaurantRepository
                .findAllByCategoryIdAndStatus(categoryId, RestaurantStatus.ACTIVE)
                .stream()
                .map(RestaurantRes::from)
                .toList();
    }

    /**
     * 배달비 기준 음식점 조회
     */
    public List<RestaurantRes> getRestaurantsByDeliveryFee(
            BigDecimal fee
    ) {

        return restaurantRepository
                .findAllByDeliveryFeeLessThanEqualAndStatus(
                        fee,
                        RestaurantStatus.ACTIVE
                )
                .stream()
                .map(RestaurantRes::from)
                .toList();
    }

    /**
     * 음식점 삭제
     */
    @Transactional
    public void deleteRestaurant(Long restaurantId) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() ->
                        new IllegalArgumentException("음식점을 찾을 수 없습니다.")
                );

        restaurantRepository.delete(restaurant);
    }

}
