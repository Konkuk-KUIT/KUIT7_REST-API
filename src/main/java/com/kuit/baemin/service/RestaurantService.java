package com.kuit.baemin.service;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.Restaurant.RestaurantStatus;
import com.kuit.baemin.dto.response.RestaurantListRes;
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
    private final RestaurantRepository RestaurantRepository;

    public RestaurantListRes getStores(Pageable pageable){
        Page<Restaurant> storePage = RestaurantRepository.findAllByStatus(
                RestaurantStatus.ACTIVE,
                pageable
                );
        return RestaurantListRes.from(storePage);

    }
}
