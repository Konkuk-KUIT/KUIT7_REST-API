package com.kuit.baemin.controller;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.dto.request.FavoriteReq;
import com.kuit.baemin.dto.response.FavoriteRes;
import com.kuit.baemin.service.FavoriteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@RequiredArgsConstructor
public class FavoriteController {

  private final FavoriteService favoriteService;

  @GetMapping
  public ApiResponse<List<FavoriteRes>> getFavorites(@RequestParam Long memberId) {
    return ApiResponse.of(favoriteService.getFavorites(memberId));
  }

  @PostMapping
  public ApiResponse<Long> addFavorite(
      @RequestParam Long memberId,
      @Valid @RequestBody FavoriteReq req) {
    return ApiResponse.of(favoriteService.addFavorite(memberId, req));
  }

  @DeleteMapping("/{favoriteId}")
  public ApiResponse<Void> removeFavorite(
      @RequestParam Long memberId,
      @PathVariable Long favoriteId) {
    favoriteService.removeFavorite(memberId, favoriteId);
    return ApiResponse.of(null);
  }
}
