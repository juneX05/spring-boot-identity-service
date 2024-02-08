package com.mwambacodes.identityservice.modules.Core.Auth.Actions.UpdateDetails;

import com.mwambacodes.identityservice.modules.Core.User.UserEntity;
import com.mwambacodes.identityservice.modules.Core.User.UserRepository;
import com.mwambacodes.identityservice.utils.Helpers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDetailsRequest {

    private String firstName;
    private String lastName;
    private String email;
    @Builder.Default
    private String errorMessage = null;
    @Builder.Default
    private HashMap<String,String> errors = new HashMap<>();


    public Boolean validate(UserRepository userRepository) {
        int error_count = 0;

        if (email == null || email.isEmpty()) {
            error_count++;
            errors.put("firstName","First Name should not be empty");
        } else {
            var userPresence = userRepository.findUserByEmail(email);
            if (userPresence.isPresent()) {
                var user = userPresence.get();
                if (!Objects.equals(user.getId(), Helpers.loggedInUser.getId())) {
                    error_count++;
                    errors.put("email", "Email exists.");
                }
            }
        }

        if (firstName == null || firstName.isEmpty()) {
            error_count++;
            errors.put("firstName","First Name should not be empty");
        }

        if (lastName == null || lastName.isEmpty()) {
            error_count++;
            errors.put("lastName","Last Name should not be empty");
        }

        if (error_count > 0) {
            errorMessage = "Validation Error";
            return false;
        }

        return true;
    }
}
