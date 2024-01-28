package com.mwambacodes.identityservice.modules.User._Modules.UserType.Actions.UserTypeList;

import com.mwambacodes.identityservice.modules.Role.RoleEntity;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserTypeListDTO {

    Long id;
    String name;
    String color;
    RoleEntity role;
    UUID uuid;

}
