package com.mwambacodes.identityservice.modules.Domain.Actions.DomainList;

import com.mwambacodes.identityservice.modules.Domain._Modules.DomainStatus.DomainStatusEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class DomainListDTO {

    @Builder
    @Data
    public static class DomainStatus {
        private int id;
        private String name;
        private String color;
    }

    @Builder
    @Data
    public static class User{
        private Long id;
        private String firstName;
        private String lastName;
    }

    private Long id;
    private String administratorEmail;
    private String name;
    private String url;
    private UUID uuid;
    private DomainStatus domainStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private User createdBy;
    private User lastUpdatedBy;


}
