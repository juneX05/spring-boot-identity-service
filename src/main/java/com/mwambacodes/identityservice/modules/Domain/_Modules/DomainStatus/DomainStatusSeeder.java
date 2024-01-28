package com.mwambacodes.identityservice.modules.Domain._Modules.DomainStatus;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DomainStatusSeeder {

    private final DomainStatusRepository domainStatusRepository;

    public void run() {
        System.out.println(DomainStatusSeeder.class + ": Start Seeding.");

        DomainStatusEntity active = DomainStatusEntity.builder()
                .id(1).name("active").color("green")
                .build();
        DomainStatusEntity pending = DomainStatusEntity.builder()
                .id(2).name("pending").color("orange")
                .build();
        DomainStatusEntity inactive = DomainStatusEntity.builder()
                .id(3).name("inactive").color("red")
                .build();
        DomainStatusEntity awaiting_approval = DomainStatusEntity.builder()
                .id(4).name("awaiting approval").color("red")
                .build();

        try {
            domainStatusRepository.save(active);
            domainStatusRepository.save(pending);
            domainStatusRepository.save(inactive);
            domainStatusRepository.save(awaiting_approval);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(DomainStatusSeeder.class + ": Done running seeders.");

    }
}
