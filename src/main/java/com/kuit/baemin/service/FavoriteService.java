package com.kuit.baemin.service;

import com.kuit.baemin.domain.Favorite;
import com.kuit.baemin.domain.Member;
import com.kuit.baemin.domain.Store;
import com.kuit.baemin.dto.request.FavoriteReq;
import com.kuit.baemin.dto.response.FavoriteRes;
import com.kuit.baemin.exception.FavoriteException;
import com.kuit.baemin.exception.MemberException;
import com.kuit.baemin.exception.StoreException;
import com.kuit.baemin.repository.FavoriteRepository;
import com.kuit.baemin.repository.MemberRepository;
import com.kuit.baemin.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

import static com.kuit.baemin.exception.errorcode.ErrorStatus.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FavoriteService {

  private final FavoriteRepository favoriteRepository;
  private final MemberRepository memberRepository;
  private final StoreRepository storeRepository;

  public List<FavoriteRes> getFavorites(Long memberId) {
    memberRepository.findById(memberId)
      .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

    return favoriteRepository.findByUserId(memberId)
      .stream()
      .map(FavoriteRes::from)
      .toList();
  }

  @Transactional
  public Long addFavorite(Long memberId, FavoriteReq req) {
    Member member = memberRepository.findById(memberId)
      .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

    Store store = storeRepository.findById(req.getStoreId())
      .orElseThrow(() -> new StoreException(STORE_NOT_FOUND));

    boolean exists = favoriteRepository.findByUserId(memberId)
      .stream()
      .anyMatch(fav -> fav.getStore().getId().equals(req.getStoreId()));

    if (exists) {
      throw new FavoriteException(FAVORITE_ALREADY_EXISTS);
    }

    Favorite favorite = new Favorite();
    favorite.setUserId(member);
    favorite.setStore(store);
    favorite.setCreatedAt(Instant.now());
    favorite.setUpdatedAt(Instant.now());
    favorite.setStatus("ACTIVE");

    Favorite saved = favoriteRepository.save(favorite);
    return saved.getId();
  }

  @Transactional
  public void removeFavorite(Long memberId, Long storeId) {
    Member member = memberRepository.findById(memberId)
      .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

    Favorite favorite = favoriteRepository.findByUserId(memberId)
      .stream()
      .filter(fav -> fav.getStore().getId().equals(storeId))
      .findFirst()
      .orElseThrow(() -> new FavoriteException(FAVORITE_NOT_FOUND));

    favorite.setStatus("DELETED");
    favorite.setUpdatedAt(Instant.now());
    favoriteRepository.save(favorite);
  }
}
