package com.mwambacodes.identityservice.modules.Core.Auth.Actions.UpdateDetails;

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
public class UpdateDetailsService {

    private final UserRepository userRepository;
    public ServiceResult init(UpdateDetailsRequest request) {

        Boolean valid = request.validate(userRepository);

        if (!valid) return error(request.getErrorMessage(), HttpStatus.SC_UNPROCESSABLE_ENTITY, request.getErrors());

        UserEntity user = Helpers.loggedInUser;

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        userRepository.save(user);

        return Helpers.success("User Details Updated Successfully");
    }

}
