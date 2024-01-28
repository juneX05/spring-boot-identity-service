package com.mwambacodes.identityservice.modules.User.Actions.SaveUser;

import com.mwambacodes.identityservice.modules.User.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSaveRequest {

    private String email;
    private String password;
    private Long domainId;
    @Builder.Default
    private String errorMessage = null;
    @Builder.Default
    private HashMap<String,String> errors = new HashMap<>();


    public Boolean validate(UserRepository userRepository) {
        int error_count = 0;

        if (email == null || email.isEmpty()) {
            error_count++;
            errors.put("email","Email should not be empty");
        } else {
            if (userRepository.findUserByEmail(email).isPresent()) {
                error_count++;
                errors.put("email", "Email already exists");
            }
        }

        if (password == null || password.isEmpty()) {
            error_count++;
            errors.put("password", "Password should not be empty");
        }

        if (domainId == null || domainId <= 0) {
            error_count++;
            errors.put("domainId", "Domain is invalid");
        }

        if (error_count > 0) {
            errorMessage = "Validation Error";
            return false;
        }

        return true;
    }
}
