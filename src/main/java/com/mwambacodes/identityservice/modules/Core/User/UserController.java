package com.mwambacodes.identityservice.modules.Core.User;

import com.mwambacodes.identityservice.modules.Core.User.Actions.UserList.UserListService;
import com.mwambacodes.identityservice.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.mwambacodes.identityservice.utils.Helpers.sendResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserListService userListService;

    @GetMapping("/list")
    public ResponseEntity<ApiResponse> index(
            @RequestParam Map<String, String> params
    ) {
        return sendResponse(userListService.init(params));
    }
//
//    @GetMapping("/save")
//    public ResponseEntity<ApiResponse> save() {
//        return sendResponse(userListService.init());
//    }

}
