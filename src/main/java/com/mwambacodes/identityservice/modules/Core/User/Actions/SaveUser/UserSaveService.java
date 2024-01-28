package com.mwambacodes.identityservice.modules.Core.User.Actions.SaveUser;

import com.mwambacodes.identityservice.modules.Core.Status.StatusEntity;
import com.mwambacodes.identityservice.modules.System.Domain.DomainEntity;
import com.mwambacodes.identityservice.modules.System.Domain.DomainRepository;
import com.mwambacodes.identityservice.modules.Core.Status.StatusRepository;
import com.mwambacodes.identityservice.modules.Core.User.UserEntity;
import com.mwambacodes.identityservice.modules.Core.User.UserRepository;
import com.mwambacodes.identityservice.modules.System.Domain._Modules.UserDomain.UserDomainEntity;
import com.mwambacodes.identityservice.modules.System.Domain._Modules.UserDomain.UserDomainRepository;
import com.mwambacodes.identityservice.modules.Core.User._Modules.UserStatus.UserStatusEntity;
import com.mwambacodes.identityservice.modules.Core.User._Modules.UserStatus.UserStatusRepository;
import com.mwambacodes.identityservice.modules.Core.User._Modules.UserType.UserTypeEntity;
import com.mwambacodes.identityservice.modules.Core.User._Modules.UserType.UserTypeRepository;
import com.mwambacodes.identityservice.utils.Helpers;
import com.mwambacodes.identityservice.utils.ServiceResult;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import static com.mwambacodes.identityservice.utils.Helpers.error;

@Service
@RequiredArgsConstructor
public class UserSaveService {

    private final UserRepository userRepository;
    private final DomainRepository domainRepository;
    private final UserDomainRepository userDomainRepository;
    private final UserStatusRepository userStatusRepository;
    private final StatusRepository statusRepository;
    private final UserTypeRepository userTypeRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public ServiceResult init(UserSaveRequest request) {

        Boolean valid = request.validate(userRepository);

        if (!valid) return error(request.getErrorMessage(), HttpStatus.SC_UNPROCESSABLE_ENTITY, request.getErrors());

        UserEntity user = userRepository.save(
                UserEntity.builder()
                        .email(request.getEmail())
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .createdAt(Date.from(Instant.now()))
                        .updatedAt(Date.from(Instant.now()))
                        .uuid(UUID.randomUUID())
                        .userStatus(userStatusRepository.getReferenceById(Integer.valueOf(UserStatusEntity.PENDING)))
                        .userType(userTypeRepository.getReferenceById(UserTypeEntity.ADMIN))
                        .build()
        );

        System.out.println("Password:" + request.getPassword());

        if (!(user.getId() > 0)) throw new RuntimeException("Failed to create User");

        return Helpers.success("User saved successfully.", user);
    }

}
