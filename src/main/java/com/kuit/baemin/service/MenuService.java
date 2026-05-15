package com.kuit.baemin.service;

import static com.kuit.baemin.exception.errorcode.ErrorStatus.MENU_NOT_FOUND;

import com.kuit.baemin.domain.menu.Menu;
import com.kuit.baemin.dto.request.MenuUpdateReq;
import com.kuit.baemin.dto.response.MenuRes;
import com.kuit.baemin.exception.MenuException;
import com.kuit.baemin.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {

    private final MenuRepository menuRepository;

    @Transactional
    public MenuRes updateMenu(Long menuId, MenuUpdateReq req) {
        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new MenuException(MENU_NOT_FOUND));
        menu.update(
                req.getName(),
                req.getDescription(),
                req.getPrice(),
                req.getImageUrl(),
                req.getIsSoldOut(),
                req.getDisplayOrder()
        );
        return MenuRes.from(menu);
    }
}
