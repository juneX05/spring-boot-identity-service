package com.mwambacodes.identityservice.modules.User._Modules.UserStatus.Mappers;

import com.mwambacodes.identityservice.modules.User._Modules.UserStatus.UserStatusEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserStatusMapper implements Function<UserStatusEntity, UserStatusEntity> {

    @Override
    public UserStatusEntity apply(UserStatusEntity userStatusEntity) {

        return UserStatusEntity.builder()
                .id(userStatusEntity.getId())
                .name(userStatusEntity.getName())
                .color(userStatusEntity.getColor())
                .build();
    }

}
