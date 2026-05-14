package com.kuit.baemin.service;

import com.kuit.baemin.domain.Menu.MenuStatus;
import com.kuit.baemin.dto.response.MenuRes;
import com.kuit.baemin.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {

    private final MenuRepository menuRepository;

    /**
     * 특정 식당의 메뉴 목록 조회
     */
    public List<MenuRes> findByRestaurantId(Long restaurantId, Pageable pageable) {

        return menuRepository.findByRestaurantIdAndStatus(restaurantId, MenuStatus.ACTIVE, pageable)
                .stream()
                .map(MenuRes::from)
                .toList();
    }
}
