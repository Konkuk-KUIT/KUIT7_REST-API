package com.kuit.baemin.service;

import com.kuit.baemin.domain.Address;
import com.kuit.baemin.domain.Member;
import com.kuit.baemin.dto.request.AddressReq;
import com.kuit.baemin.dto.response.AddressRes;
import com.kuit.baemin.exception.AddressException;
import com.kuit.baemin.exception.MemberException;
import com.kuit.baemin.repository.AddressRepository;
import com.kuit.baemin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

import static com.kuit.baemin.exception.errorcode.ErrorStatus.ADDRESS_NOT_FOUND;
import static com.kuit.baemin.exception.errorcode.ErrorStatus.MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AddressService {

  private final AddressRepository addressRepository;
  private final MemberRepository memberRepository;

  public List<AddressRes> getAddresses(Long memberId) {
    memberRepository.findById(memberId)
      .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

    return addressRepository.findByMemberId(memberId)
      .stream()
      .map(AddressRes::from)
      .toList();
  }

  @Transactional
  public Long addAddress(Long memberId, AddressReq req) {
    Member member = memberRepository.findById(memberId)
      .orElseThrow(() -> new MemberException(MEMBER_NOT_FOUND));

    if (req.getIsDefault()) {
      addressRepository.findByMemberId(memberId)
        .forEach(addr -> {
          addr.setIsDefault(false);
          addr.setUpdatedAt(Instant.now());
          addressRepository.save(addr);
        });
    }

    Address address = new Address();
    address.setUser(member);
    address.setZipcode(req.getZipcode());
    address.setMainAddress(req.getMainAddress());
    address.setDetailAddress(req.getDetailAddress());
    address.setNickname(req.getNickname());
    address.setIsDefault(req.getIsDefault());
    address.setEntrancePassword(req.getEntrancePassword());
    address.setDescriptionAddress(req.getDescriptionAddress());
    address.setCreatedAt(Instant.now());
    address.setUpdatedAt(Instant.now());
    address.setStatus("ACTIVE");

    Address saved = addressRepository.save(address);
    return saved.getId();
  }

  @Transactional
  public void updateAddress(Long memberId, Long addressId, AddressReq req) {
    Address address = addressRepository.findByIdAndMemberId(addressId, memberId)
      .orElseThrow(() -> new AddressException(ADDRESS_NOT_FOUND));

    if (req.getIsDefault()) {
      addressRepository.findByMemberId(memberId)
        .forEach(addr -> {
          if (!addr.getId().equals(addressId)) {
            addr.setIsDefault(false);
            addr.setUpdatedAt(Instant.now());
            addressRepository.save(addr);
          }
        });
    }

    address.setZipcode(req.getZipcode());
    address.setMainAddress(req.getMainAddress());
    address.setDetailAddress(req.getDetailAddress());
    address.setNickname(req.getNickname());
    address.setIsDefault(req.getIsDefault());
    address.setEntrancePassword(req.getEntrancePassword());
    address.setDescriptionAddress(req.getDescriptionAddress());
    address.setUpdatedAt(Instant.now());

    addressRepository.save(address);
  }

  @Transactional
  public void deleteAddress(Long memberId, Long addressId) {
    Address address = addressRepository.findByIdAndMemberId(addressId, memberId)
      .orElseThrow(() -> new AddressException(ADDRESS_NOT_FOUND));

    address.setStatus("DELETED");
    address.setUpdatedAt(Instant.now());
    addressRepository.save(address);
  }
}
