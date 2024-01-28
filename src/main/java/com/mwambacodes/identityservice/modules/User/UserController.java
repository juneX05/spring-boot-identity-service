package com.mwambacodes.identityservice.modules.User;

import com.mwambacodes.identityservice.modules.User.Actions.ListUser.UserListService;
import com.mwambacodes.identityservice.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mwambacodes.identityservice.utils.Helpers.sendResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserListService userListService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> index() {
        return sendResponse(userListService.init());
    }

    @GetMapping("/save")
    public ResponseEntity<ApiResponse> save() {
        return sendResponse(userListService.init());
    }

}
