package com.mwambacodes.identityservice.utils;

import com.mwambacodes.identityservice.modules.Core.User.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Helpers {

    public static Date startProcessingTime;
    public static UserEntity loggedInUser = null;

    public static ResponseEntity<ApiResponse> sendResponse(ServiceResult result, Map<String, String> headers) {
//        ResponseEntity.BodyBuilder response;
//        HttpHeaders httpHeaders = new HttpHeaders();
//        headers.forEach(httpHeaders::add);

        ResponseEntity<ApiResponse> response = ResponseEntity
                .status(result.code)
                .body(
                        ApiResponse.builder()
                                .startProcessing(startProcessingTime)
                                .status(result.status)
                                .finishProcessing(Date.from(Instant.now()))
                                .code(result.code)
                                .message(result.message)
                                .data(result.data)
                                .build()
                );

//        headers.forEach((key,value) -> response.getHeaders().add(key,value));

        return response;

    }

    public static ResponseEntity<ApiResponse> sendResponse(ServiceResult result) {
        return sendResponse(result, new HashMap<String, String>());
    }

    public static ServiceResult result(Boolean status, String message, Integer code, Object data) {
        return ServiceResult.builder()
                .status(status)
                .code(code)
                .message(message)
                .data(data)
                .build();
    }

    public static ServiceResult success(String message, Object data) {
        return result(true, message, 200, data);
    }

    public static ServiceResult success(String message, Integer code, Object data) {
        return result(true, message, code, data);
    }

    public static ServiceResult success() {
        return success("Success", 200, new HashMap<>());
    }

    public static ServiceResult success(String message) {
        return success(message, 200, new HashMap<>());
    }

    public static ServiceResult success(Object data) {
        return success("Success", 200, data);
    }

    public static ServiceResult error(String message, Integer code, Object data) {
        return result(false, message, code, data);
    }

    public static ServiceResult error(String message, Object data) {
        return error(message, 500, data);
    }

    public static ServiceResult error(String message) {
        return error(message, 500, new HashMap<>());
    }

    public static ServiceResult error(String message, Integer code) {
        return error(message, code, new HashMap<>());
    }

//    public static void checkPermission(String permissionName) {
//        if (Helpers.loggedInUser == null) {
//            throw new InsufficientAuthenticationException("You are not Authorized");
//        }
//
//        List<String> permissions = Helpers.loggedInUser.getPermissionsList().stream().toList();
//
//        if (!permissions.contains(permissionName)) {
//            throw new InsufficientAuthenticationException("You are not Allowed to perform this action");
//        }
//    }
}
