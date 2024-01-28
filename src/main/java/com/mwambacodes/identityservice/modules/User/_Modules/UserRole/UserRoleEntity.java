package com.mwambacodes.identityservice.modules.User._Modules.UserRole;

import com.mwambacodes.identityservice.modules.Role.RoleEntity;
import com.mwambacodes.identityservice.modules.User.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "user_roles"
)
public class UserRoleEntity {

    @EmbeddedId
    UserRoleKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    UserEntity user;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    @ToString.Exclude
    RoleEntity role;

}
