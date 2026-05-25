package com.kuit.baemin.service;

import com.kuit.baemin.dto.response.RestaurantRes;
import com.kuit.baemin.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    /**
     * 식당 조회
     */
    public List<RestaurantRes> findAll(Pageable pageable) {

        return restaurantRepository.findAll(pageable)
                .stream()
                .map(RestaurantRes::from)
                .toList();
    }
}
