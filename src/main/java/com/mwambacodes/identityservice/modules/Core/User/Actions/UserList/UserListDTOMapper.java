package com.mwambacodes.identityservice.modules.Core.User.Actions.UserList;

import com.mwambacodes.identityservice.modules.Core.Role.RoleEntity;
import com.mwambacodes.identityservice.modules.Core.User.UserEntity;
import com.mwambacodes.identityservice.modules.Core.User._Modules.UserStatus.UserStatusEntity;
import com.mwambacodes.identityservice.modules.Core.User._Modules.UserType.UserTypeEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserListDTOMapper implements Function<UserEntity, UserListDTO> {

    private UserListDTO.UserStatus getUserStatus(UserStatusEntity userStatusEntity) {
        return UserListDTO.UserStatus.builder()
                .id(userStatusEntity.getId())
                .name(userStatusEntity.getName())
                .color(userStatusEntity.getColor())
                .build();
    }

    private UserListDTO.UserType getUserType(UserTypeEntity userTypeEntity) {
        RoleEntity roleEntity = userTypeEntity.getRole();
        return UserListDTO.UserType.builder()
                .role(UserListDTO.UserType.Role.builder()
                        .id(roleEntity.getId())
                        .name(roleEntity.getName())
                        .build())
                .color(userTypeEntity.getColor())
                .name(userTypeEntity.getName())
                .build();
    }

    private UserListDTO.User getUser(UserEntity userEntity) {
        return UserListDTO.User.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .build();
    }

    @Override
    public UserListDTO apply(UserEntity userEntity) {
        UserListDTO userListDTO = new UserListDTO();

        userListDTO.setId(userEntity.getId());
        userListDTO.setFirstName(userEntity.getFirstName());
        userListDTO.setLastName(userEntity.getLastName());
        userListDTO.setEmail(userEntity.getEmail());
        userListDTO.setUuid(userEntity.getUuid());
        userListDTO.setUserStatus(getUserStatus(userEntity.getUserStatus()));
        userListDTO.setCreatedAt(userEntity.getCreatedAt());
        userListDTO.setUpdatedAt(userEntity.getLastUpdatedAt());
        userListDTO.setUserType(getUserType(userEntity.getUserType()));
        userListDTO.setCreatedBy(getUser(userEntity.getCreatedBy()));
        userListDTO.setLastUpdatedBy(getUser(userEntity.getLastUpdatedBy()));

        return userListDTO;
    }
}
