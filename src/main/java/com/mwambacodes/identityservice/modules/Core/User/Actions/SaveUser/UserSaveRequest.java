package com.mwambacodes.identityservice.modules.Core.User.Actions.SaveUser;

import com.mwambacodes.identityservice.modules.Core.User.UserRepository;
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
    private String firstName;
    private String lastName;
    private String password;
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

        if (firstName == null || firstName.isEmpty()) {
            error_count++;
            errors.put("firstName", "First Name should not be empty");
        }

        if (lastName == null || lastName.isEmpty()) {
            error_count++;
            errors.put("lastName", "Last Name should not be empty");
        }

        if (error_count > 0) {
            errorMessage = "Validation Error";
            return false;
        }

        return true;
    }
}
