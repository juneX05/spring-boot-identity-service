package com.mwambacodes.identityservice.modules.System.Domain.Actions.DomainCreate;

import com.mwambacodes.identityservice.modules.Core.Media.Action.MediaUpload.MediaUploadService;
import com.mwambacodes.identityservice.modules.Core.Media.MediaEntity;
import com.mwambacodes.identityservice.modules.Core.Status.StatusEntity;
import com.mwambacodes.identityservice.modules.Core.Status.StatusRepository;
import com.mwambacodes.identityservice.modules.Core.User.UserEntity;
import com.mwambacodes.identityservice.modules.System.Domain.DomainEntity;
import com.mwambacodes.identityservice.modules.System.Domain.DomainRepository;
import com.mwambacodes.identityservice.modules.System.Domain._Modules.DomainStatus.DomainStatusEntity;
import com.mwambacodes.identityservice.modules.System.Domain._Modules.DomainStatus.DomainStatusRepository;
import com.mwambacodes.identityservice.modules.Core.User.Actions.SaveUser.UserSaveRequest;
import com.mwambacodes.identityservice.modules.Core.User.Actions.SaveUser.UserSaveService;
import com.mwambacodes.identityservice.modules.System.Domain._Modules.UserDomain.UserDomainEntity;
import com.mwambacodes.identityservice.modules.System.Domain._Modules.UserDomain.UserDomainRepository;
import com.mwambacodes.identityservice.utils.Helpers;
import com.mwambacodes.identityservice.utils.ServiceResult;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DomainCreateService {
    private final DomainRepository domainRepository;
    private final DomainStatusRepository domainStatusRepository;
    private final UserDomainRepository userDomainRepository;
    private final StatusRepository statusRepository;

    private final UserSaveService userSaveService;
    private final MediaUploadService mediaUploadService;

    @Transactional
    public ServiceResult init(DomainCreateRequest request) {

        Boolean valid = request.validate(domainRepository);
        if (!valid) {
            return Helpers.error(request.getErrorMessage(), HttpStatus.SC_UNPROCESSABLE_ENTITY, request.getErrors());
        }

        MediaEntity media = mediaUploadService.init(request.getLogo());

        DomainEntity domain = domainRepository.save(DomainEntity.builder()
                .name(request.getName())
                .logo(media)
                .commissionPercentage(request.getCommissionPercentage())
                .createdAt(LocalDateTime.now())
                .createdBy((UserEntity) Helpers.loggedInUser)
                .updatedAt(LocalDateTime.now())
                .lastUpdatedBy((UserEntity) Helpers.loggedInUser)
                .administratorEmail(request.getAdministratorEmail())
                .url(request.getUrl())
                .domainStatus(domainStatusRepository.getReferenceById(Integer.valueOf(DomainStatusEntity.PENDING)))
                .uuid(UUID.randomUUID())
                .build());

        if (!(domain.getId() > 0)) {
            throw new RuntimeException("Failed to create Domain");
        }

        //register a new user
        ServiceResult saveUserResult = userSaveService.init(
                UserSaveRequest.builder()
                        .email(request.getAdministratorEmail())
                        .firstName(request.getAdministratorFirstName())
                        .lastName(request.getAdministratorLastName())
                        .password(String.valueOf(UUID.randomUUID()))
                        .build()
        );

        UserEntity user = (UserEntity) saveUserResult.getData();

        StatusEntity status = statusRepository.findById(Integer.valueOf(StatusEntity.PENDING)).get();

        UserDomainEntity userDomain = userDomainRepository.save(UserDomainEntity.builder()
                .domain(domain)
                .user(user)
                .status(status)
                .build());

        if (!(userDomain.getId() > 0)) throw new RuntimeException("Failed to create Domain: User Domain Creation Failed.");

        return Helpers.success("Domain Registration successful.");
    }
}
