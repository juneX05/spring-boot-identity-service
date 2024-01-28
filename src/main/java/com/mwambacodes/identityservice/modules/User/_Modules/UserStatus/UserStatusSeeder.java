package com.mwambacodes.identityservice.modules.User._Modules.UserStatus;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserStatusSeeder {

    private final UserStatusRepository userStatusRepository;

    public void run() {
        System.out.println(UserStatusSeeder.class + ": Start Seeding.");

        List<UserStatusEntity> userStatuses = new ArrayList<>();
        userStatuses.add(UserStatusEntity.builder()
                .id(1).name("active").color("green")
                .build());
        userStatuses.add(UserStatusEntity.builder()
                .id(2).name("pending").color("orange")
                .build());
        userStatuses.add(UserStatusEntity.builder()
                .id(3).name("suspended").color("red")
                .build());


        try {
            userStatusRepository.saveAll(userStatuses);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println(UserStatusSeeder.class + ": Done running seeders.");

    }
}
