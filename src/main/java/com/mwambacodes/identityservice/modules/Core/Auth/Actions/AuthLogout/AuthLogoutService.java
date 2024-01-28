package com.mwambacodes.identityservice.modules.Core.Auth.Actions.AuthLogout;

import com.mwambacodes.identityservice.utils.Helpers;
import com.mwambacodes.identityservice.utils.ServiceResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthLogoutService {
    public ServiceResult init(HttpServletRequest httpServletRequest) throws ServletException {
        System.out.println( Helpers.loggedInUser.getAuthorities());
        httpServletRequest.logout();
        SecurityContextHolder.getContext().setAuthentication(null);
        return Helpers.success("Logged Out Successfully");
    }
}
