package com.mwambacodes.identityservice.modules.Core.Auth.Actions.AuthLogin;

import com.mwambacodes.identityservice.jwt.JWTUtil;
import com.mwambacodes.identityservice.modules.Core.Role.RoleEntity;
import com.mwambacodes.identityservice.modules.Core.User.UserEntity;
import com.mwambacodes.identityservice.utils.Helpers;
import com.mwambacodes.identityservice.utils.ServiceResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthLoginService {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    public ServiceResult init(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                )
        );

        UserEntity principal = (UserEntity) authentication.getPrincipal();
        //List<String> roles = principal.getUserRoles()
        List<String> roles = principal.getRoles()
                        .stream().map(RoleEntity::getName)
                        .toList();
        String token = jwtUtil.issueToken(principal.getUsername(), roles);

        HashMap<String, Object> data = new HashMap<>();
        data.put("token", token);

        return Helpers.success(data);
    }

}
