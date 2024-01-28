package com.mwambacodes.identityservice.modules.User._Modules.UserDomain;

import com.mwambacodes.identityservice.modules.Domain.DomainEntity;
import com.mwambacodes.identityservice.modules.Status.StatusEntity;
import com.mwambacodes.identityservice.modules.User.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "user_domains"
)
public class UserDomainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_id")
    @ToString.Exclude
    UserEntity user;

    @ManyToOne
    @JoinColumn(nullable = false, name = "domain_id")
    @ToString.Exclude
    DomainEntity domain;

    @ManyToOne
    @JoinColumn(nullable = false, name = "status_id")
    @ColumnDefault(StatusEntity.PENDING)
    @ToString.Exclude
    private StatusEntity status;

}
