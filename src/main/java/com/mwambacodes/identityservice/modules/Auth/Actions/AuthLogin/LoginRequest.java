package com.mwambacodes.identityservice.modules.Auth.Actions.AuthLogin;

public record LoginRequest(
        String email,
        String password
) {
}
