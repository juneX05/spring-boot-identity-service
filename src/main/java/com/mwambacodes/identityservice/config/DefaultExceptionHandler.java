package com.mwambacodes.identityservice.config;

import com.mwambacodes.identityservice.utils.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;

import static com.mwambacodes.identityservice.utils.Helpers.error;
import static com.mwambacodes.identityservice.utils.Helpers.sendResponse;


@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse> handleException(BadCredentialsException e,
                                                       HttpServletRequest request)
    {
        return sendResponse(error(e.getMessage(), HttpStatus.UNAUTHORIZED.value(), new HashMap<>()));
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<ApiResponse> handleException(InsufficientAuthenticationException e,
                                                    HttpServletRequest request)
    {
        return sendResponse(error(e.getMessage(), HttpStatus.FORBIDDEN.value(), new HashMap<>()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse> handleException(AccessDeniedException e,
                                                    HttpServletRequest request)
    {
        return sendResponse(error(e.getMessage(), HttpStatus.FORBIDDEN.value(), new HashMap<>()));
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ApiResponse> handleException(Exception e,
//                                                       HttpServletRequest request)
//    {
//        return sendResponse(error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), new HashMap<>()));
//    }
}
