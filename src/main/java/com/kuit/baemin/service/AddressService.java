package com.kuit.baemin.service;

import com.kuit.baemin.domain.address.Address;
import com.kuit.baemin.domain.address.AddressStatus;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.member.MemberStatus;
import com.kuit.baemin.domain.member_address.MemberAddress;
import com.kuit.baemin.dto.response.AddressDelRes;
import com.kuit.baemin.dto.response.AddressRes;
import com.kuit.baemin.exception.AddressException;
import com.kuit.baemin.exception.GeneralException;
import com.kuit.baemin.exception.MemberException;
import com.kuit.baemin.repository.AddressRepository;
import com.kuit.baemin.repository.MemberAddressRepository;
import com.kuit.baemin.repository.MemberRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kuit.baemin.exception.errorcode.ErrorStatus.ADDRESS_NOT_FOUND;
import static com.kuit.baemin.exception.errorcode.ErrorStatus.FORBIDDEN;
import static com.kuit.baemin.exception.errorcode.ErrorStatus.MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AddressService {

    private final MemberAddressRepository memberAddressRepository;
    private final AddressRepository addressRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long createAddress(Long memberId, String name, String category) {
        // 1. 회원 존재 여부 확인
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

        // 2. Address 엔티티 생성 및 저장
        // 명세서에 위도/경도가 없으므로 임의의 값(0.0)과 기본 상태(ACTIVE 등)를 설정합니다.
        Address address = Address.builder()
                .name(name)
                .latitude(127.0) // api를 통한 수정 필요
                .longitude(37.7)
                .status(AddressStatus.ACTIVE) // AddressStatus에 정의된 기본값 사용
                .build();
        addressRepository.save(address);

        // 3. MemberAddress 엔티티 생성 및 저장 (연관관계 매핑)
        MemberAddress memberAddress = MemberAddress.builder()
                .member(member)
                .address(address)
                .category(category)
                .build();
        memberAddressRepository.save(memberAddress);

        // 4. 명세서 요구사항에 따라 생성된 Address의 ID 반환
        return address.getId();
    }

    @Transactional
    public AddressDelRes deleteAddress(Long memberId, Long loginMemberId, Long addressId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

        if (!member.getId().equals(loginMemberId)) {
            throw new GeneralException(FORBIDDEN);
        }

        MemberAddress memberAddress = memberAddressRepository.findByMemberIdAndAddressId(memberId, addressId)
                .orElseThrow(() -> new AddressException(ADDRESS_NOT_FOUND));

        memberAddressRepository.delete(memberAddress);

        Address address = memberAddress.getAddress();
        address.changeStatus(AddressStatus.INACTIVE);

        // DTO에 담아서 반환
        return new AddressDelRes(address.getId(), address.getStatus().name(), "주소가 정상적으로 삭제되었습니다.");
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