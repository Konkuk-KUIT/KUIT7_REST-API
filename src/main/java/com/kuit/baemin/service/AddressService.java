package com.kuit.baemin.service;

import com.kuit.baemin.common.dto.ApiResponse;
import com.kuit.baemin.domain.address.Address;
import com.kuit.baemin.domain.member.MemberStatus;
import com.kuit.baemin.domain.member_address.MemberAddress;
import com.kuit.baemin.dto.request.AddressReq;
import com.kuit.baemin.dto.response.AddressRes;
import com.kuit.baemin.exception.AddressException;
import com.kuit.baemin.exception.GeneralException;
import com.kuit.baemin.repository.AddressRepository;
import com.kuit.baemin.repository.MemberAddressRepository;
import com.kuit.baemin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kuit.baemin.exception.errorcode.ErrorStatus.ADDRESS_NOT_FOUND;
import static com.kuit.baemin.exception.errorcode.ErrorStatus.FORBIDDEN;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AddressService {

    private final MemberAddressRepository memberAddressRepository;
    private final AddressRepository addressRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long createAddress(Long memberId, AddressReq request) {
        // 1. 회원 존재 확인 (야매 회원 검증)
        var member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        // 2. Address 엔티티 생성 및 저장
        Address address = Address.builder()
                .name(request.getName())
                .status(MemberStatus.ACTIVE) // 아까 터졌던 status 예방용!
                .build();

        Address savedAddress = addressRepository.save(address);

        // 3. 생성된 addressId 반환
        return savedAddress.getId();
    }

    public AddressRes getAddress(Long addressId, Long loginMemberId) {
        // 1. 주소 존재 확인 (404)
        MemberAddress memberAddress = memberAddressRepository.findById(addressId)
                .orElseThrow(() -> new AddressException(ADDRESS_NOT_FOUND));

        // 2. 본인 확인 (403)
        // 주소 데이터에 저장된 memberId와 현재 로그인한 memberId를 비교합니다.
        if (!memberAddress.getMember().getId().equals(loginMemberId)) {
            throw new GeneralException(FORBIDDEN);
        }

        // 3. DTO 변환 후 반환
        return AddressRes.from(memberAddress);
    }
}