package com.mwambacodes.identityservice.modules.System.Domain;

import com.mwambacodes.identityservice.modules.Core.Media.MediaEntity;
import com.mwambacodes.identityservice.modules.System.Domain._Modules.DomainStatus.DomainStatusEntity;
import com.mwambacodes.identityservice.modules.Core.User.UserEntity;
import com.mwambacodes.identityservice.modules.System.Domain._Modules.UserDomain.UserDomainEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "domains",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "name_unique_key",
                        columnNames = "name"
                ),
                @UniqueConstraint(
                        name = "uuid_unique_key",
                        columnNames = "uuid"
                ),
        }
)
public class DomainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "url")
    private String url;

    @Column(nullable = false, name = "administrator_email")
    private String administratorEmail;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(nullable = false, name = "created_by")
    private UserEntity createdBy;

    @Column(nullable = false, name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(nullable = false, name = "last_updated_by")
    private UserEntity lastUpdatedBy;

    @ManyToOne
    @JoinColumn(nullable = false, name = "domain_status_id")
    @ColumnDefault(DomainStatusEntity.PENDING)
    @ToString.Exclude
    private DomainStatusEntity domainStatus;

    @Column(nullable = false, name = "uuid", updatable = false, unique = true)
    @UuidGenerator
    private UUID uuid;

    @OneToMany(mappedBy = "domain")
    @ToString.Exclude
    Set<UserDomainEntity> userDomains;

    @ManyToOne
    @JoinColumn(nullable = true, name = "logo_id")
    @ToString.Exclude
    private MediaEntity logo;

    @Column(nullable = false, name = "commission_percentage")
    private Double commissionPercentage;

}
