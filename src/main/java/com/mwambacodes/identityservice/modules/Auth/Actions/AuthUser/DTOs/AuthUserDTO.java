package com.mwambacodes.identityservice.modules.Auth.Actions.AuthUser.DTOs;

import com.mwambacodes.identityservice.modules.Domain.DomainEntity;
import com.mwambacodes.identityservice.modules.User._Modules.UserStatus.UserStatusEntity;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class AuthUserDTO {

    @Builder
    @Data
    public static class UserType {
        private Long id;
        private String name;
        private String color;
    }

    Long id;
    String email;
    String firstName;
    String lastName;
    Date createdAt;
    Date updatedAt;
    UserStatusEntity userStatus;
    List<DomainEntity> userDomains;
    Set<String> permissions;
    UUID uuid;
    List<String> roles;
    UserType userType;
}
