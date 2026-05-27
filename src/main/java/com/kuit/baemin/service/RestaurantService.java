package com.kuit.baemin.service;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.Restaurant.RestaurantStatus;
import com.kuit.baemin.dto.request.RegisterRestaurantReq;
import com.kuit.baemin.dto.request.UpdateRestaurantReq;
import com.kuit.baemin.dto.response.RestaurantRes;
import com.kuit.baemin.exception.RestaurantException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kuit.baemin.exception.errorcode.ErrorStatus.RESTAURNAT_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    /**
     * 신규 가게 등록
     */
    @Transactional
    public Long registerRestaurant(RegisterRestaurantReq req) {
        // 가게 이름 중복 확인
        if (restaurantRepository.existsByName(req.getStoreName())) {
            throw new RestaurantException(ErrorStatus.DUPLICATE_STORE_NAME);
        }

        Restaurant restaurant = Restaurant.builder()
                .name(req.getStoreName())
                .minimum_delivery_price(req.getMinimum_delivery_price())
                .status(RestaurantStatus.CLOSED)
                .build();

        Restaurant saved = restaurantRepository.save(restaurant);
        return saved.getId();
    }

    public List<RestaurantRes> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();

        return restaurants.stream()
                .map(RestaurantRes::from)
                .toList();
    }

    public RestaurantRes getRestaurant(Long restaurantId) {
        Restaurant restaurant =  findRestaurantById(restaurantId);
        return RestaurantRes.from(restaurant);
    }

    @Transactional
    public Long updateRestaurant(Long restaurantId, UpdateRestaurantReq req) {

        Restaurant restaurant = findRestaurantById(restaurantId);

        if (req.getStoreName() != null) {

            // 이름을 변경하는 경우
            if(!restaurant.getName().equals(req.getStoreName())) {
                // 가게 이름 중복 확인
                if (restaurantRepository.existsByName(req.getStoreName())) {
                    throw new RestaurantException(ErrorStatus.DUPLICATE_STORE_NAME);
                }

                restaurant.updateName(req.getStoreName());
            }
        }

        if(req.getMinimum_delivery_price() != null) {
            restaurant.updateMinimumDeliveryPrice(req.getMinimum_delivery_price());
        }

        return restaurantId;
    }

    public Long deleteRestaurant(Long restaurantId) {
    if(!restaurantRepository.existsById(restaurantId)) {
        throw new RestaurantException(RESTAURNAT_NOT_FOUND);
    }

    restaurantRepository.deleteById(restaurantId);
    return restaurantId;
    }

    public Restaurant findRestaurantById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantException(RESTAURNAT_NOT_FOUND));
    }
}
