package com.mwambacodes.identityservice.modules.Core.Auth.Actions.ChangePassword;

import com.mwambacodes.identityservice.modules.Core.User.UserEntity;
import com.mwambacodes.identityservice.modules.Core.User.UserRepository;
import com.mwambacodes.identityservice.utils.Helpers;
import com.mwambacodes.identityservice.utils.ServiceResult;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.mwambacodes.identityservice.utils.Helpers.error;

@Service
@RequiredArgsConstructor
public class ChangePasswordService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ServiceResult init(ChangePasswordRequest request) {

        UserEntity user = (UserEntity) Helpers.loggedInUser;

        Boolean valid = request.validate(user,passwordEncoder);

        if (!valid) return error(request.getErrorMessage(), HttpStatus.SC_UNPROCESSABLE_ENTITY, request.getErrors());

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        userRepository.save(user);

        return Helpers.success("Password Updated Successfully");
    }

}
