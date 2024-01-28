package com.mwambacodes.identityservice.modules.Auth.Actions.AuthUser.DTOs;

import com.mwambacodes.identityservice.modules.Domain.DomainEntity;
import com.mwambacodes.identityservice.modules.Role.RoleEntity;
import com.mwambacodes.identityservice.modules.User.UserEntity;
import com.mwambacodes.identityservice.modules.User._Modules.UserDomain.UserDomainEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@Service
public class AuthUserMapper implements Function<UserEntity, AuthUserDTO> {



    @Override
    public AuthUserDTO apply(UserEntity user) {
        List<DomainEntity> domains = user.getUserDomains() != null
                ? user.getUserDomains()
                .stream()
                .map(UserDomainEntity::getDomain)
                .toList()
                : null;

        return AuthUserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .id(user.getId())
                .userDomains(domains)
                .userStatus(user.getUserStatus())
                .permissions(user.getPermissionsList())
                .roles(user.getRoles().stream().map(RoleEntity::getName).toList())
                .email(user.getEmail())
                .updatedAt(user.getUpdatedAt())
                .uuid(user.getUuid())
                .createdAt(user.getCreatedAt())
                .userType(
                        AuthUserDTO.UserType.builder()
                                .color(user.getUserType().getColor())
                                .name(user.getUserType().getName())
                                .id(user.getUserType().getId())
                                .build()
                )
                .build();
    }
}

