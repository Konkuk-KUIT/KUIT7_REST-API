package com.kuit.baemin.service;

import com.kuit.baemin.domain.address.Address;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.dto.request.CreateAddressReq;
import com.kuit.baemin.dto.response.AddressRes;
import com.kuit.baemin.exception.MemberException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.AddressRepository;
import com.kuit.baemin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AddressService {

    private final AddressRepository addressRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long createAddress(Long memberId, CreateAddressReq req) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(ErrorStatus.MEMBER_NOT_FOUND));

        Address address = Address.builder()
                .member(member)
                .roadAddress(req.getRoadAddress())
                .detailAddress(req.getDetailAddress())
                .isDefault(req.getIsDefault())
                .build();
        return addressRepository.save(address).getId();
    }

    public List<AddressRes> getAddresses(Long memberId) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(ErrorStatus.MEMBER_NOT_FOUND));
        return addressRepository.findAllByMemberId(memberId).stream()
                .map(AddressRes::from)
                .collect(Collectors.toList());
    }
}