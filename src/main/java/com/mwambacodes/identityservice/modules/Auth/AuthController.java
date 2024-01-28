package com.mwambacodes.identityservice.modules.Auth;

import com.mwambacodes.identityservice.modules.Auth.Actions.AuthLogin.AuthLoginService;
import com.mwambacodes.identityservice.modules.Auth.Actions.AuthLogin.LoginRequest;
import com.mwambacodes.identityservice.modules.Auth.Actions.AuthLogout.AuthLogoutService;
import com.mwambacodes.identityservice.modules.Auth.Actions.AuthUser.AuthUserService;
import com.mwambacodes.identityservice.utils.ApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.mwambacodes.identityservice.utils.Helpers.sendResponse;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthLoginService loginService;
    private final AuthUserService authUserService;
    private final AuthLogoutService authLogoutService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(
            @RequestBody LoginRequest request
    ) {
        return sendResponse(loginService.init(request));
    }

    @GetMapping("/user")
    public ResponseEntity<ApiResponse> user() {
        return sendResponse(authUserService.init());
    }


    @GetMapping("/logout")
    public ResponseEntity<ApiResponse> logout(HttpServletRequest httpServletRequest) throws ServletException {
        return sendResponse(authLogoutService.init(httpServletRequest));
    }

}
