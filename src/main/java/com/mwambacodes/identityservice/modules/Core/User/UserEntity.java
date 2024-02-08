package com.mwambacodes.identityservice.modules.Core.User;

import com.mwambacodes.identityservice.modules.Core.Status.StatusEntity;
import com.mwambacodes.identityservice.modules.Core.Permission.PermissionEntity;
import com.mwambacodes.identityservice.modules.Core.Role.RoleEntity;
import com.mwambacodes.identityservice.modules.System.Domain._Modules.UserDomain.UserDomainEntity;
import com.mwambacodes.identityservice.modules.Core.User._Modules.UserPermission.UserPermissionEntity;
import com.mwambacodes.identityservice.modules.Core.User._Modules.UserStatus.UserStatusEntity;
import com.mwambacodes.identityservice.modules.Core.User._Modules.UserType.UserTypeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "email_unique_key",
                        columnNames = "email"
                ),
        }
)
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false, name = "last_name")
    private String lastName;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(nullable = false, name = "password")
    private String password;

    @Column(nullable = false, name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(nullable = true, name = "created_by")
    @ToString.Exclude
    private UserEntity createdBy;

    @Column(nullable = false, name = "last_updated_at")
    private LocalDateTime lastUpdatedAt;

    @ManyToOne
    @JoinColumn(nullable = true, name = "last_updated_by")
    @ToString.Exclude
    private UserEntity lastUpdatedBy;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_status_id")
    @ColumnDefault(UserStatusEntity.PENDING)
    @ToString.Exclude
    private UserStatusEntity userStatus;

    @ManyToOne
    @JoinColumn(nullable = false, name = "user_type_id")
    @ToString.Exclude
    private UserTypeEntity userType;

    @Column(nullable = false, name = "uuid", updatable = false, unique = true)
    @UuidGenerator
    private UUID uuid;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = {
                    @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id")
            }
    )
    @ToString.Exclude
    private Collection<RoleEntity> roles;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    Collection<UserDomainEntity> userDomains;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    Collection<UserPermissionEntity> userPermissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {


        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String permission : getPermissionsList()) {
            authorities.add(new SimpleGrantedAuthority(permission));
        }

        return authorities;
    }

    public Set<String> getPermissionsList() {
        //Get Permissions
        Set<String> permissions = new HashSet<>();
        List<PermissionEntity> collection = new ArrayList<>();

        for (RoleEntity role: roles) {
            permissions.add(role.getName());
            collection.addAll(role.getPermissions());
        }

        for (PermissionEntity permission : collection) {
            permissions.add(permission.getName());
        }

        if (userPermissions != null) {
            for (UserPermissionEntity userPermission: userPermissions) {
                if (Objects.equals(userPermission.getStatus().getId(), Integer.valueOf(StatusEntity.INACTIVE))) {
                    permissions.remove(userPermission.getPermission().getName());
                } else {
                    permissions.add(userPermission.getPermission().getName());
                }
            }
        }

        return permissions;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
