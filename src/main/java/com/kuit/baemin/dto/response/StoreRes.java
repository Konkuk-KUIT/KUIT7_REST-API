package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.Restaurant.RestaurantStatus;
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
  private RestaurantStatus status;

  public static StoreRes from(Restaurant restaurant) {
    return new StoreRes(
        restaurant.getId(),
        restaurant.getName(),
        restaurant.getAddress(),
        restaurant.getStatus()
    );
  }
}
