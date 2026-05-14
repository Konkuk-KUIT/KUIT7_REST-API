package com.kuit.baemin.service;


import com.kuit.baemin.domain.address.Address;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.domain.member.MemberStatus;
import com.kuit.baemin.dto.request.AddressReq;
import com.kuit.baemin.dto.response.AddressRes;
import com.kuit.baemin.exception.MemberException;
import com.kuit.baemin.exception.errorcode.ErrorStatus;
import com.kuit.baemin.repository.AddressRepository;
import com.kuit.baemin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;



    public AddressRes addAddress(Long memberId, AddressReq req){
        Member member = memberRepository.findByIdAndStatus(memberId , MemberStatus.ACTIVE)
                .orElseThrow(() -> new MemberException(ErrorStatus.MEMBER_NOT_FOUND));

        Address address = Address.builder()
                .member(member)
                .addressCategory(req.getAddressCategory())
                .isDefault(req.getIsDefault())
                .entrancePassword(req.getEntrancePassword())
                .userLatitude(req.getUserLatitude())
                .userLongitude(req.getUserLongitude())
                .status("ACTIVE")
                .build();

        addressRepository.save(address);

        return AddressRes.builder()
                .addressId(address.getId())
                .build();
    }


}
