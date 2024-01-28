package com.mwambacodes.identityservice.modules.Core.Role;

import com.mwambacodes.identityservice.modules.Core.User.UserEntity;
import com.mwambacodes.identityservice.modules.Core.Permission.PermissionEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "roles",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "name_domain_id_unique_key",
                        columnNames = "name,domain_id"
                )
        }
)
public class RoleEntity {

    public static final Long DEVELOPER = 1L;
    public static final Long SUPER_ADMIN = 2L;
    public static final Long ADMIN = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "uuid", updatable = false, unique = true)
    @UuidGenerator
    private UUID uuid;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @ToString.Exclude
    private Set<UserEntity> users;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn( name="role_id" ),
            inverseJoinColumns = @JoinColumn( name="permission_id" )
    )
    @ToString.Exclude
    private Collection<PermissionEntity> permissions;


}
