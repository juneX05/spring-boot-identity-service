package com.mwambacodes.identityservice.modules.User._Modules.UserPermission;

import com.mwambacodes.identityservice.modules.Permission.PermissionEntity;
import com.mwambacodes.identityservice.modules.Status.StatusEntity;
import com.mwambacodes.identityservice.modules.User.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "user_permissions"
)
public class UserPermissionEntity {

    @EmbeddedId
    UserPermissionKey id;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    UserEntity user;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @MapsId("permissionId")
    @JoinColumn(name = "permission_id")
    @ToString.Exclude
    PermissionEntity permission;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    @ToString.Exclude
    StatusEntity status;

}
