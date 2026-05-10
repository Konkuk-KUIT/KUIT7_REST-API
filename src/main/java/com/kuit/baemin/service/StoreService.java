package com.kuit.baemin.service;

import com.kuit.baemin.domain.Store;
import com.kuit.baemin.dto.response.StoreRes;
import com.kuit.baemin.exception.StoreException;
import com.kuit.baemin.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.kuit.baemin.exception.errorcode.ErrorStatus.STORE_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreService {

  private final StoreRepository storeRepository;

  public List<StoreRes> getStores() {
    return storeRepository.findAll()
      .stream()
      .filter(store -> "ACTIVE".equals(store.getStatus()))
      .map(StoreRes::from)
      .toList();
  }

  public StoreRes getStore(Long storeId) {
    Store store = storeRepository.findById(storeId)
      .filter(s -> "ACTIVE".equals(s.getStatus()))
      .orElseThrow(() -> new StoreException(STORE_NOT_FOUND));
    return StoreRes.from(store);
  }
}
