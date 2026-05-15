package com.kuit.baemin.service;

import com.kuit.baemin.domain.address.Address;
import com.kuit.baemin.domain.address.AddressStatus;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.dto.request.AddressCreateReq;
import com.kuit.baemin.dto.response.AddressRes;
import com.kuit.baemin.exception.MemberException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.AddressRepository;
import com.kuit.baemin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AddressService {

    private final AddressRepository addressRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long createAddress(AddressCreateReq req) {
        Member member = memberRepository.findById(req.getMemberId())
                .orElseThrow(() -> new MemberException(ErrorStatus.MEMBER_NOT_FOUND));

        Address address = Address.builder()
                .member(member)
                .addressType(req.getAddressType())
                .address(req.getAddress())
                .latitude(req.getLatitude())
                .longitude(req.getLongitude())
                .status(AddressStatus.ACTIVE)
                .build();

        return addressRepository.save(address).getId();
    }

    public List<AddressRes> getMemberAddresses(Long memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new MemberException(ErrorStatus.MEMBER_NOT_FOUND);
        }

        return addressRepository.findByMemberId(memberId)
                .stream()
                .map(AddressRes::from)
                .toList();
    }
}
