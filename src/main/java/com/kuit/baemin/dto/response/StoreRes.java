package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoreRes {
  private Long id;
  private String name;
  private String address;
  private String status;

  public static StoreRes from(Store store) {
    return new StoreRes(
        store.getId(),
        store.getName(),
        store.getStoreAddress(),
        store.getStatus()
    );
  }
}
