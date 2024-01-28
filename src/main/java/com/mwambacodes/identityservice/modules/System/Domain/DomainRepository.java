package com.mwambacodes.identityservice.modules.System.Domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface DomainRepository extends JpaRepository<DomainEntity, Long>, JpaSpecificationExecutor<DomainEntity> {
    Optional<DomainEntity> findByName(String name);
    Optional<DomainEntity> findByUrl(String url);
    Optional<DomainEntity> findByAdministratorEmail(String administratorEmail);
}
