package com.kuit.baemin.service;

import com.kuit.baemin.domain.address.Address;
import com.kuit.baemin.domain.address.AddressStatus;
import com.kuit.baemin.domain.member.Member;
import com.kuit.baemin.dto.request.CreateAddressReq;
import com.kuit.baemin.dto.request.UpdateAddressReq;
import com.kuit.baemin.dto.response.AddressListRes;
import com.kuit.baemin.dto.response.AddressRes;
import com.kuit.baemin.dto.response.DefaultAddressRes;
import com.kuit.baemin.dto.response.DeleteAddressRes;
import com.kuit.baemin.exception.GeneralException;
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
    public AddressRes createAddress(Long userId, CreateAddressReq req){
        Member member = memberRepository.findById(userId)
                .orElseThrow(()-> new MemberException(ErrorStatus.MEMBER_NOT_FOUND));

        Address address = Address.builder()
                .addressName(req.getAddressName())
                .address(req.getAddress())
                .isDefault(req.getIsDefault())
                .latitude(req.getLatitude())
                .longitude(req.getLongitude())
                .member(member)
                .build();
        Address savedAddress = addressRepository.save(address);

        return AddressRes.from(savedAddress);
    }
    public AddressListRes getAddresses(Long userId){
        memberRepository.findById(userId)
                .orElseThrow(()-> new MemberException(ErrorStatus.MEMBER_NOT_FOUND));
        List<Address> addresses = addressRepository.findAllByMemberIdAndStatus(
                userId,
                AddressStatus.ACTIVE
        );
        return AddressListRes.of(userId, addresses);
    }
    @Transactional
    public AddressRes updateAddress(Long addressId, UpdateAddressReq req){
        if(req.isEmpty()){
            throw new GeneralException(ErrorStatus.ADDRESS_EMPTY_UPDATE_VALUE);

        }
        Address address = addressRepository.findById(addressId)
                .orElseThrow(()-> new GeneralException(ErrorStatus.ADDRESS_NOT_FOUND));
        if(address.isDeleted()){
            throw new GeneralException(ErrorStatus.ADDRESS_ALREADY_DELETED);
        }
        address.updateInfo(
                req.getAddress(),
                req.getAddressName(),
                req.getIsDefault(),
                req.getLatitude(),
                req.getLongitude()
        );
        return AddressRes.from(address);
    }
    @Transactional
    public DeleteAddressRes deleteAddress(Long addressId){
        Address address = addressRepository.findById(addressId)
                .orElseThrow(()-> new GeneralException(ErrorStatus.ADDRESS_NOT_FOUND));
        if(address.isDeleted()){
            throw new GeneralException(ErrorStatus.ADDRESS_ALREADY_DELETED);
        }
        address. delete();
        return DeleteAddressRes.from(address);
    }

    @Transactional
    public DefaultAddressRes changeDefaultAddress(Long addressId){
        Address targetAddress = addressRepository.findById(addressId)
                .orElseThrow(()-> new GeneralException(ErrorStatus.ADDRESS_NOT_FOUND));

        if(targetAddress.isDeleted()){
            throw new GeneralException(ErrorStatus.ADDRESS_ALREADY_DELETED);
        }
        if(targetAddress.isAlreadyDefault()){
            throw new GeneralException(ErrorStatus.ADDRESS_ALREADY_DEFAULT);
        }
        Long userId = targetAddress.getMember().getId();

        List<Address> defaultAddresses = addressRepository.findAllByMemberIdAndIsDefaultTrueAndStatus(
                userId,
                AddressStatus.ACTIVE
        );
        defaultAddresses.forEach(Address::unsetDefault);
        targetAddress.setDefault();
        return DefaultAddressRes.from(targetAddress);
    }
}
