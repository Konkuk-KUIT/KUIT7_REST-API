package com.kuit.baemin.domain.address;

import com.kuit.baemin.domain.BaseEntity;
import com.kuit.baemin.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="addresses")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @Column(name="address_name", length =50)
    private String addressName;

    @Column(name = "address", length = 255, nullable = false)
    private String address;

    @Column(name = "is_default", nullable = false)
    private Boolean isDefault;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private AddressStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Member member;

    @Builder
    public Address(
            String addressName,
            String address,
            Boolean isDefault,
            Double latitude,
            Double longitude,
            Member member
    ){
        this.addressName = addressName;
        this.address = address;
        this.isDefault = isDefault !=null ? isDefault:false;
        this. latitude = latitude;
        this. longitude = longitude;
        this.member = member;
        this.status = AddressStatus.ACTIVE;
    }
    public void updateInfo(
            String address,
            String addressName,
            Boolean isDefault,
            Double latitude,
            Double longitude
    ){
        if(addressName!=null){
            this.addressName = addressName;
        }
        if(address!=null){
            this.address = address;
        }
        if(isDefault!=null){
            this.isDefault = isDefault;
        }
        if(latitude !=null){
            this.latitude = latitude;
        }
        if(longitude!=null) {
            this.longitude = longitude;
        }
    }
    public boolean isDeleted(){
        return this.status == AddressStatus.DELETED;
    }
    public void delete(){
        this.status = AddressStatus.DELETED;
    }
    public void setDefault(){
        this.isDefault = true;
    }
    public void unsetDefault(){
        this.isDefault = false;
    }
    public boolean isAlreadyDefault(){
        return Boolean.TRUE.equals(this.isDefault);
    }
}
