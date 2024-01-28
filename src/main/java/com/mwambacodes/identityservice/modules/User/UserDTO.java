package com.mwambacodes.identityservice.modules.User;

import com.mwambacodes.identityservice.modules.User._Modules.UserDomain.UserDomainEntity;
import com.mwambacodes.identityservice.modules.User._Modules.UserStatus.UserStatusEntity;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record UserDTO(
    Long id,
    String email,
    Date createdAt,
    Date updatedAt,
    UserStatusEntity userStatus,
    List<UserDomainEntity> userDomains,
    UUID uuid,
    List<String> roles
) {
}
