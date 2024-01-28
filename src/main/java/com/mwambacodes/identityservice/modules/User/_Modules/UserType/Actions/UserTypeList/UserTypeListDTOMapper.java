package com.mwambacodes.identityservice.modules.User._Modules.UserType.Actions.UserTypeList;

import com.mwambacodes.identityservice.modules.User._Modules.UserType.UserTypeEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserTypeListDTOMapper implements Function<UserTypeEntity, UserTypeListDTO> {

    @Override
    public UserTypeListDTO apply(UserTypeEntity userTypeEntity) {

        return UserTypeListDTO.builder()
                .color(userTypeEntity.getColor())
                .role(userTypeEntity.getRole())
                .name(userTypeEntity.getName())
                .id(userTypeEntity.getId())
                .build();

    }
}
