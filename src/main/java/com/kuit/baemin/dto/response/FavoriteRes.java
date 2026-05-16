package com.kuit.baemin.dto.response;

import com.kuit.baemin.domain.Favorite;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteRes {
  private Long id;
  private Long restaurantId;
  private String restaurantName;

  public static FavoriteRes from(Favorite favorite) {
    return new FavoriteRes(
        favorite.getId(),
        favorite.getStore().getId(),
        favorite.getStore().getName()
    );
  }
}
