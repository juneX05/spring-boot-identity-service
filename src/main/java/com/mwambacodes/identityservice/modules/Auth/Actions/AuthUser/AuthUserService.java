package com.mwambacodes.identityservice.modules.Auth.Actions.AuthUser;

import com.mwambacodes.identityservice.modules.Auth.Actions.AuthUser.DTOs.AuthUserMapper;
import com.mwambacodes.identityservice.modules.User.UserEntity;
import com.mwambacodes.identityservice.utils.Helpers;
import com.mwambacodes.identityservice.utils.ServiceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserService {
    private final AuthUserMapper authUserMapper;

    public ServiceResult init() {
        if (Helpers.loggedInUser != null) {
            var user = authUserMapper.apply( (UserEntity) Helpers.loggedInUser );
            return Helpers.success(user);
        }

        return Helpers.error("User not logged in");
    }

}
