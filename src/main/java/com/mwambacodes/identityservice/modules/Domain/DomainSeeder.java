package com.mwambacodes.identityservice.modules.Domain;

import com.mwambacodes.identityservice.modules.Permission.PermissionEntity;
import com.mwambacodes.identityservice.modules.Permission.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DomainSeeder {

    private final PermissionRepository permissionRepository;

    public void run() {
        System.out.println(DomainSeeder.class + ": Start Seeding.");

        List<PermissionEntity> permissions = new ArrayList<>();

        permissions.add(PermissionEntity.builder()
                .name("Domain.list")
                .title("List Domains")
                .description("List All Domains")
                .module("Domain")
                .build()
        );

        permissions.add(PermissionEntity.builder()
                .name("Domain.create")
                .title("Create Domain")
                .description("Add or Create a Domain")
                .module("Domain")
                .build()
        );

        permissions.add(PermissionEntity.builder()
                .name("Domain.view")
                .title("View Domain Details")
                .description("View Details of a Domain")
                .module("Domain")
                .build()
        );

        permissions.add(PermissionEntity.builder()
                .name("Domain.edit")
                .title("Edit Domain Details")
                .description("Edit Details of a Domain")
                .module("Domain")
                .build()
        );

        permissions.add(PermissionEntity.builder()
                .name("Domain.activate")
                .title("Activate Domain")
                .description("Activate a Domain")
                .module("Domain")
                .build()
        );


        permissions.add(PermissionEntity.builder()
                .name("Domain.suspend")
                .title("Suspend Domain")
                .description("Suspend a Domain")
                .module("Domain")
                .build()
        );

        try {
            permissionRepository.saveAll(permissions);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        System.out.println(DomainSeeder.class + ": Done running seeders.");

    }
}
