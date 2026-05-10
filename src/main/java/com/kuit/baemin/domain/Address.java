package com.kuit.baemin.domain;

import com.kuit.baemin.domain.Member.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "address")
public class Address {
    @Id
    @Column(name = "address_id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Member user;

    @Size(max = 10)
    @NotNull
    @Column(name = "zipcode", nullable = false, length = 10)
    private String zipcode;

    @Size(max = 225)
    @NotNull
    @Column(name = "main_address", nullable = false, length = 225)
    private String mainAddress;

    @Size(max = 225)
    @NotNull
    @Column(name = "detail_address", nullable = false, length = 225)
    private String detailAddress;

    @Size(max = 30)
    @ColumnDefault("'주소1'")
    @Column(name = "nickname", length = 30)
    private String nickname;

    @Size(max = 100)
    @Column(name = "entrance_password", length = 100)
    private String entrancePassword;

    @Size(max = 225)
    @Column(name = "description_address", length = 225)
    private String descriptionAddress;

    @NotNull
    @ColumnDefault("1")
    @Column(name = "is_default", nullable = false)
    private Boolean isDefault;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Size(max = 255)
    @Column(name = "status")
    private String status;


}