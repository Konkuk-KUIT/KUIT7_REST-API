package com.kuit.baemin.service;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.dto.response.MenuRes;
import com.kuit.baemin.dto.response.RestaurantDetailRes;
import com.kuit.baemin.dto.response.RestaurantListRes;
import com.kuit.baemin.exception.RestaurantException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.MenuRepository;
import com.kuit.baemin.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // 읽기 전용 트랜잭션 (조회 속도 향상)
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    /**
     * API 3: 가게 목록 조회 (페이징)
     */
    public RestaurantListRes getRestaurants(Pageable pageable) {
        // 1. DB에서 페이징된 엔티티 목록을 가져옴
        Page<Restaurant> restaurantPage = restaurantRepository.findAll(pageable);

        // 2. 엔티티 리스트 -> DTO 리스트로 변환 (stream 활용)
        List<RestaurantDetailRes> dtoList = restaurantPage.getContent().stream()
                .map(RestaurantDetailRes::from)
                .collect(Collectors.toList());

        // 3. 페이징 정보와 함께 응답 DTO 만들기
        return RestaurantListRes.builder()
                .restaurants(dtoList)
                .totalPage(restaurantPage.getTotalPages())
                .totalElements(restaurantPage.getTotalElements())
                .isFirst(restaurantPage.isFirst())
                .isLast(restaurantPage.isLast())
                .build();
    }

    /**
     * API 4: 가게 상세 조회
     */
    public RestaurantDetailRes getRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantException(ErrorStatus.RESTAURANT_NOT_FOUND));

        return RestaurantDetailRes.from(restaurant);
    }

    /**
     * API 5: 메뉴판 조회
     */
    public List<MenuRes> getMenus(Long restaurantId) {
        // 가게가 존재하는지 먼저 확인
        if (!restaurantRepository.existsById(restaurantId)) {
            throw new RestaurantException(ErrorStatus.RESTAURANT_NOT_FOUND);
        }

        return menuRepository.findByRestaurantId(restaurantId).stream()
                .map(MenuRes::from)
                .collect(Collectors.toList());
    }
}