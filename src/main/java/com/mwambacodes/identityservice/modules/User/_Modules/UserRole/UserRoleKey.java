package com.mwambacodes.identityservice.modules.User._Modules.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable

public class UserRoleKey implements Serializable {

    @Column(nullable = false, name = "user_id")
    Long userId;

    @Column(nullable = false, name = "role_id")
    Long roleId;

}
