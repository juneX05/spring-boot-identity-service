package com.mwambacodes.identityservice.modules.Domain;

import java.util.function.Function;

public class DomainMapper implements Function<DomainEntity, DomainEntity> {

    @Override
    public DomainEntity apply(DomainEntity domainEntity) {
        return DomainEntity.builder().build();
    }

}
