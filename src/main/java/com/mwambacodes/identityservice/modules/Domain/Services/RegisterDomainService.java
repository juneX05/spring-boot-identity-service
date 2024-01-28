package com.mwambacodes.identityservice.modules.Domain.Services;

import com.mwambacodes.identityservice.modules.Domain.DomainEntity;
import com.mwambacodes.identityservice.modules.Domain.DomainRepository;
import com.mwambacodes.identityservice.modules.Domain.Requests.RegisterDomainRequest;
import com.mwambacodes.identityservice.modules.Domain._Modules.DomainStatus.DomainStatusEntity;
import com.mwambacodes.identityservice.modules.Domain._Modules.DomainStatus.DomainStatusRepository;
import com.mwambacodes.identityservice.modules.User.Actions.SaveUser.UserSaveRequest;
import com.mwambacodes.identityservice.modules.User.Actions.SaveUser.UserSaveService;
import com.mwambacodes.identityservice.utils.Helpers;
import com.mwambacodes.identityservice.utils.ServiceResult;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegisterDomainService {
    private final DomainRepository domainRepository;
    private final DomainStatusRepository domainStatusRepository;

    private final UserSaveService userSaveService;

    @Transactional
    public ServiceResult init(RegisterDomainRequest request) {

        Boolean valid = request.validate(domainRepository);
        if (!valid) {
            return Helpers.error(request.getErrorMessage(), HttpStatus.SC_UNPROCESSABLE_ENTITY, request.getErrors());
        }

        var domain = DomainEntity.builder()
                .name(request.getName())
                .administratorEmail(request.getAdministratorEmail())
                .url(request.getUrl())
                .domainStatus(domainStatusRepository.getReferenceById(Integer.valueOf(DomainStatusEntity.PENDING)))
                .uuid(UUID.randomUUID())
                .build();

        domainRepository.save(domain);

        //register a new user
        ServiceResult result = userSaveService.init(
                UserSaveRequest.builder()
                        .email(request.getAdministratorEmail())
                        .domainId(domain.getId())
                        .password(String.valueOf(UUID.randomUUID()))
                        .build()
        );

        if (result.getStatus()) return Helpers.success("Domain registration successful");

        return Helpers.error("Failed to register Domain");
    }
}
