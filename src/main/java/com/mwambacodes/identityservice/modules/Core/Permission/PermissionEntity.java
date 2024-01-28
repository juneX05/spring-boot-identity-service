package com.mwambacodes.identityservice.modules.Core.Permission;

import com.mwambacodes.identityservice.modules.Core.Role.RoleEntity;
import com.mwambacodes.identityservice.modules.Core.User._Modules.UserPermission.UserPermissionEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "permissions",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "name_unique_key",
                        columnNames = "name"
                ),
                @UniqueConstraint(
                        name = "title_unique_key",
                        columnNames = "title"
                )
        }
)
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "module")
    private String module;

    @Column(nullable = false, name = "description")
    private String description;

    @Column(nullable = false, name = "uuid", updatable = false, unique = true)
    @UuidGenerator
    private UUID uuid;

    @ManyToMany(mappedBy = "permissions")
    @ToString.Exclude
    private Set<RoleEntity> roles;

    @OneToMany(mappedBy = "permission")
    @ToString.Exclude
    Set<UserPermissionEntity> userPermissions;

}
