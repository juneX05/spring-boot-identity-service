package com.mwambacodes.identityservice.modules.Core.Auth;

import com.mwambacodes.identityservice.modules.Core.Auth.Actions.AuthLogin.AuthLoginService;
import com.mwambacodes.identityservice.modules.Core.Auth.Actions.AuthLogin.LoginRequest;
import com.mwambacodes.identityservice.modules.Core.Auth.Actions.AuthLogout.AuthLogoutService;
import com.mwambacodes.identityservice.modules.Core.Auth.Actions.AuthUser.AuthUserService;
import com.mwambacodes.identityservice.modules.Core.Auth.Actions.ChangePassword.ChangePasswordRequest;
import com.mwambacodes.identityservice.modules.Core.Auth.Actions.ChangePassword.ChangePasswordService;
import com.mwambacodes.identityservice.modules.Core.Auth.Actions.UpdateDetails.UpdateDetailsRequest;
import com.mwambacodes.identityservice.modules.Core.Auth.Actions.UpdateDetails.UpdateDetailsService;
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
    private final ChangePasswordService changePasswordService;
    private final UpdateDetailsService updateDetailsService;

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

    @PostMapping("/change-password")
    public ResponseEntity<ApiResponse> changePassword(
            @RequestBody ChangePasswordRequest request
    ) {
        return sendResponse(changePasswordService.init(request));
    }

    @PostMapping("/update-details")
    public ResponseEntity<ApiResponse> updateDetails(
            @RequestBody UpdateDetailsRequest request
    ) {
        return sendResponse(updateDetailsService.init(request));
    }

}
