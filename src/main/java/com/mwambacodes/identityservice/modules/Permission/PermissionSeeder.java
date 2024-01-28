package com.mwambacodes.identityservice.modules.Permission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PermissionSeeder {

    private final PermissionRepository permissionRepository;

    public void run() {
        System.out.println(PermissionSeeder.class + ": Start Seeding.");

        List<PermissionEntity> permissions = new ArrayList<>();
        var module = "Permission";

        permissions.add(PermissionEntity.builder()
                .name("Permission.list")
                .title("List Permissions")
                .description("List All Permissions")
                .module(module)
                .build()
        );

        permissions.add(PermissionEntity.builder()
                .name("Permission.create")
                .title("Create Permission")
                .description("Create a Permission")
                .module(module)
                .build()
        );

        permissions.add(PermissionEntity.builder()
                .name("Permission.view")
                .title("View Permission Details")
                .description("View details of a Permission")
                .module(module)
                .build()
        );

        permissions.add(PermissionEntity.builder()
                .name("Permission.edit")
                .title("Edit Permission Details")
                .description("Edit details of a Permission")
                .module(module)
                .build()
        );

        permissions.add(PermissionEntity.builder()
                .name("Permission.delete")
                .title("Delete Permission")
                .description("Delete or Remove a Permission")
                .module(module)
                .build()
        );

        try {
            permissionRepository.saveAll(permissions);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        System.out.println(PermissionSeeder.class + ": Done running seeders.");

    }
}
