package com.kuit.baemin.service;

import com.kuit.baemin.domain.menu.Menu;
import com.kuit.baemin.domain.menu.MenuStatus;
import com.kuit.baemin.dto.response.MenuRes;
import com.kuit.baemin.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {

    private final MenuRepository menuRepository;

    public List<MenuRes> getMenusByStore(Long storeId) {
        List<Menu> menus = menuRepository.findByStoreIdAndStatus(storeId, MenuStatus.ACTIVE);

        return menus.stream()
                .map(MenuRes::from)
                .collect(Collectors.toList());
    }
}