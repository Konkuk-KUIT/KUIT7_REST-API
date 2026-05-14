package com.kuit.baemin.service;

import com.kuit.baemin.domain.Menu.Menu;
import com.kuit.baemin.domain.Store.Store;
import com.kuit.baemin.dto.response.MenuRes;
import com.kuit.baemin.exception.MenuException;
import com.kuit.baemin.exception.StoreException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.MenuRepository;
import com.kuit.baemin.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MenuService {
    private final StoreRepository storeRepository;
    private final MenuRepository menuRepository;

    public List<MenuRes> getStoreMenus(Long storeId){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(ErrorStatus.STORE_NOT_FOUND));

        List<Menu> menus = menuRepository.findAllByStoreAndStatus(store, "ACTIVE");
        if (menus == null){throw new MenuException(ErrorStatus.MENU_NOT_FOUND);}

        // 3. List<Menu> (엔티티)를 List<MenuRes> (DTO)로 변환하여 반환
        return menus.stream()
                .map(MenuRes::from)
                .collect(Collectors.toList());
    }


}
