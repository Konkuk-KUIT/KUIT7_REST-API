package com.kuit.baemin.service;


import com.kuit.baemin.domain.Favorite;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.dto.response.FavoriteStoreRes;
import com.kuit.baemin.exception.MemberException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.FavoriteRepository;
import com.kuit.baemin.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FavoriteService {
    private final MemberRepository memberRepository;
    private final FavoriteRepository favoriteRepository;

    public List<FavoriteStoreRes> getFavoriteStore(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(ErrorStatus.MEMBER_NOT_FOUND));

        List<Favorite> favorites = favoriteRepository.findAllByMemberAndStatus(member, "ACTIVE");

        return favorites.stream()
                .map(FavoriteStoreRes::from) // DTO의 from 메서드 호출
                .collect(Collectors.toList());

    }

}
