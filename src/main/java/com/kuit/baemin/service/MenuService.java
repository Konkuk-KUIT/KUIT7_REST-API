package com.kuit.baemin.service;

import com.kuit.baemin.domain.Menu.MenuStatus;
import com.kuit.baemin.dto.response.MenuRes;
import com.kuit.baemin.exception.RestaurantException;
import com.kuit.baemin.repository.MenuRepository;
import com.kuit.baemin.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kuit.baemin.exception.errorcode.ErrorStatus.RESTAURANT_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    /**
     * 특정 식당의 메뉴 목록 조회
     */
    public List<MenuRes> findByRestaurantId(Long restaurantId, Pageable pageable) {

        // 존재하는 식당인지 확인
        restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantException(RESTAURANT_NOT_FOUND));

        return menuRepository.findByRestaurantIdAndStatus(restaurantId, MenuStatus.ACTIVE, pageable)
                .stream()
                .map(MenuRes::from)
                .toList();
    }
}
