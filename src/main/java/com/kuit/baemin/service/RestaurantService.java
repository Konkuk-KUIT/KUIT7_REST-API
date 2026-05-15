package com.kuit.baemin.service;

import com.kuit.baemin.domain.category.Category;
import com.kuit.baemin.domain.restaurant.Restaurant;
import com.kuit.baemin.domain.restaurant.RestaurantStatus;
import com.kuit.baemin.dto.request.RestaurantCreateReq;
import com.kuit.baemin.dto.response.PageRes;
import com.kuit.baemin.dto.response.RestaurantDetailRes;
import com.kuit.baemin.dto.response.RestaurantRes;
import com.kuit.baemin.exception.GeneralException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.CategoryRepository;
import com.kuit.baemin.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public Long create(RestaurantCreateReq req) {
        Category category = categoryRepository.findById(req.getCategoryId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.CATEGORY_NOT_FOUND));

        Restaurant restaurant = Restaurant.builder()
                .category(category)
                .name(req.getName())
                .phone(req.getPhone())
                .address(req.getAddress())
                .latitude(req.getLatitude())
                .longitude(req.getLongitude())
                .minOrderPrice(req.getMinOrderPrice())
                .deliveryFee(req.getDeliveryFee())
                .status(RestaurantStatus.ACTIVE)
                .build();

        return restaurantRepository.save(restaurant).getId();
    }

    public PageRes<RestaurantRes> list(Long categoryId, Pageable pageable) {
        var page = (categoryId == null)
                ? restaurantRepository.findAllByStatus(RestaurantStatus.ACTIVE, pageable)
                : restaurantRepository.findAllByCategory_IdAndStatus(categoryId, RestaurantStatus.ACTIVE, pageable);
        return PageRes.of(page, RestaurantRes::from);
    }

    public RestaurantDetailRes getDetail(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.RESTAURANT_NOT_FOUND));
        return RestaurantDetailRes.from(restaurant);
    }
}
