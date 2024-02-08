package com.mwambacodes.identityservice.modules.Core.Auth.Actions.ChangePassword;

import com.mwambacodes.identityservice.modules.Core.User.UserEntity;
import com.mwambacodes.identityservice.modules.Core.User.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordRequest {

    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
    @Builder.Default
    private String errorMessage = null;
    @Builder.Default
    private HashMap<String,String> errors = new HashMap<>();


    public Boolean validate(UserEntity user, PasswordEncoder passwordEncoder) {
        int error_count = 0;

        if (oldPassword == null || oldPassword.isEmpty()) {
            error_count++;
            errors.put("oldPassword","Old Password should not be empty");
        } else {
            if (!passwordEncoder.matches(oldPassword,user.getPassword())) {
                error_count++;
                errors.put("oldPassword", "Old Password should be your current password");
            }
        }

        if (newPassword == null || newPassword.isEmpty()) {
            error_count++;
            errors.put("newPassword","New Password should not be empty");
        }

        if (confirmPassword == null || confirmPassword.isEmpty()) {
            error_count++;
            errors.put("confirmPassword","Confirm Password should not be empty");
        }

        if (confirmPassword != null && newPassword != null) {
            if (!confirmPassword.equals(newPassword)) {
                error_count++;
                errors.put("confirmPassword", "New Password and Confirm Password should match.");
            }
        }

        if (error_count > 0) {
            errorMessage = "Validation Error";
            return false;
        }

        return true;
    }
}
