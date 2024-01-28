package com.mwambacodes.identityservice.modules.Status;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StatusSeeder {

    private final StatusRepository statusRepository;

    public void run() {
        System.out.println(StatusSeeder.class + ": Start Seeding.");

        StatusEntity active = StatusEntity.builder()
                .id(1).name("active").color("green")
                .build();
        StatusEntity pending = StatusEntity.builder()
                .id(2).name("pending").color("orange")
                .build();
        StatusEntity inactive = StatusEntity.builder()
                .id(3).name("inactive").color("red")
                .build();

        try {
            statusRepository.save(active);
            statusRepository.save(pending);
            statusRepository.save(inactive);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(StatusSeeder.class + ": Done running seeders.");

    }
}
