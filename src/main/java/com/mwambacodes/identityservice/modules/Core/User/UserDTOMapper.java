package com.mwambacodes.identityservice.modules.Core.User;

import com.mwambacodes.identityservice.modules.Core.Role.RoleEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<UserEntity, UserDTO> {
    @Override
    public UserDTO apply(UserEntity user) {
        return new UserDTO(
                user.getId(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getUserStatus(),
                user.getUserDomains().stream().toList(),
                user.getUuid(),
                user.getRoles().stream().map(RoleEntity::getName).toList()
                //user.getUserRoles().stream().map( userRoleEntity -> userRoleEntity.getRole().getName()).toList()
        );
    }
}
