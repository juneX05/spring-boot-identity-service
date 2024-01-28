package com.mwambacodes.identityservice.modules.Core.Auth.Actions.AuthLogin;

public record LoginRequest(
        String email,
        String password
) {
}
