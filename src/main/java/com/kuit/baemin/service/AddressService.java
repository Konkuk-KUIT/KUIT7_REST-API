package com.kuit.baemin.service;

import static com.kuit.baemin.exception.errorcode.ErrorStatus.MEMBER_NOT_FOUND;

import com.kuit.baemin.domain.address.Address;
import com.kuit.baemin.domain.address.AddressStatus;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.dto.request.AddressCreateReq;
import com.kuit.baemin.dto.response.AddressRes;
import com.kuit.baemin.exception.MemberException;
import com.kuit.baemin.repository.AddressRepository;
import com.kuit.baemin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AddressService {

    private final AddressRepository addressRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public AddressRes createAddress(AddressCreateReq req) {
        Member member = memberRepository.findById(req.getMemberId())
                .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

        Address address = Address.builder()
                .addressType(req.getAddressType())
                .addressName(req.getAddressName())
                .roadAddress(req.getRoadAddress())
                .detailAddress(req.getDetailAddress())
                .latitude(req.getLatitude())
                .longitude(req.getLongitude())
                .isDefault(req.getIsDefault())
                .status(AddressStatus.active)
                .member(member)
                .build();

        return AddressRes.from(addressRepository.save(address));
    }
}
