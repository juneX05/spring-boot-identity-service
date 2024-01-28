package com.mwambacodes.identityservice.modules.System.Domain.Actions.DomainList;

import com.mwambacodes.identityservice.modules.System.Domain.DomainEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class DomainListDTOMapper implements Function<DomainEntity,DomainListDTO> {

    @Override
    public DomainListDTO apply(DomainEntity domainEntity) {
        return DomainListDTO.builder()
                .name(domainEntity.getName())
                .createdAt(domainEntity.getCreatedAt())
                .id(domainEntity.getId())
                .domainStatus(DomainListDTO.DomainStatus.builder()
                        .color(domainEntity.getDomainStatus().getColor())
                        .name(domainEntity.getDomainStatus().getName())
                        .id(domainEntity.getDomainStatus().getId())
                        .build()
                )
                .createdBy(DomainListDTO.User.builder()
                        .firstName(domainEntity.getCreatedBy().getFirstName())
                        .lastName(domainEntity.getCreatedBy().getLastName())
                        .id(domainEntity.getCreatedBy().getId())
                        .build()
                )
                .url(domainEntity.getUrl())
                .uuid(domainEntity.getUuid())
                .lastUpdatedBy(DomainListDTO.User.builder()
                        .firstName(domainEntity.getLastUpdatedBy().getFirstName())
                        .lastName(domainEntity.getLastUpdatedBy().getLastName())
                        .id(domainEntity.getLastUpdatedBy().getId())
                        .build()
                )
                .administratorEmail(domainEntity.getAdministratorEmail())
                .updatedAt(domainEntity.getUpdatedAt())
                .build();
    }
}
