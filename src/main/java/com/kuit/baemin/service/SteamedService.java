package com.kuit.baemin.service;

import com.kuit.baemin.domain.Restaurant.Restaurant;
import com.kuit.baemin.domain.Steamed;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.steamed.SteamedStatus;
import com.kuit.baemin.dto.response.SliceRes;
import com.kuit.baemin.dto.response.SteamedRes;
import com.kuit.baemin.exception.SteamedException;
import com.kuit.baemin.repository.SteamedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kuit.baemin.exception.errorcode.ErrorStatus.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SteamedService {
    private final SteamedRepository steamedRepository;
    private final MemberService memberService;
    private final RestaurantService restaurantService;

    /**
     * 찜 목록 조회
     */
    public SliceRes<SteamedRes> getAllSteamed(Long memberId, Pageable pageable) {
        Member member = memberService.findMemberById(memberId);
        Slice<Steamed> steamedSlice = steamedRepository.findByMemberOrderByCreatedAtDesc(member, pageable);
        Slice<SteamedRes> steamedResSlice = steamedSlice.map(SteamedRes::from);
        return SliceRes.from(steamedResSlice);
    }

    /**
     * 가게 찜하기
     */
    @Transactional
    public Long steamRestaurant(Long memberId, Long restaurantId) {
        Member member = memberService.findMemberById(memberId);
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

        if(steamedRepository.existsByStore(restaurant)) {
            throw new SteamedException(DUPLICATE_STEAMED);
        }

        Steamed steamed = Steamed.builder()
                .member(member)
                .store(restaurant)
                .status(SteamedStatus.ACTIVE)
                .build();

        steamedRepository.save(steamed);

        return member.getId();
    }

    /**
     * 찜 삭제
     */
    @Transactional
    public Long unsteamrestaurant(Long memberId, Long restaurantId) {
        Member member = memberService.findMemberById(memberId);
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

        Steamed steamed = steamedRepository.findByMemberAndStore(member, restaurant)
                .orElseThrow(() -> new SteamedException(STEAMED_NOT_FOUND));

        steamedRepository.delete(steamed);

        return steamed.getId();
    }
}
