package com.mwambacodes.identityservice.modules.Role;

import com.mwambacodes.identityservice.modules.Permission.PermissionEntity;
import com.mwambacodes.identityservice.modules.Permission.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleSeeder {

    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;

    public void run() {
        System.out.println(RoleSeeder.class + ": Start Seeding.");

        //seedPermissions();
        seedData();

        System.out.println(RoleSeeder.class + ": Done running seeders.");

    }

    public void seedData() {
        List<RoleEntity> roles = new ArrayList<>();

        roles.add(RoleEntity.builder()
                .name("Developer")
                .build()
        );

        roles.add(RoleEntity.builder()
                .name("SuperAdmin")
                .build()
        );

        roles.add(RoleEntity.builder()
                .name("Admin")
                .build()
        );

        try {
            roleRepository.saveAll(roles);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void seedPermissions() {
        List<PermissionEntity> permissions = new ArrayList<>();
        var module = "Role";

        permissions.add(PermissionEntity.builder()
                .name("Role.list")
                .title("List Roles")
                .description("List All Roles")
                .module(module)
                .build()
        );

        permissions.add(PermissionEntity.builder()
                .name("Role.create")
                .title("Create Role")
                .description("Create a Role")
                .module(module)
                .build()
        );

        permissions.add(PermissionEntity.builder()
                .name("Role.view")
                .title("View Role Details")
                .description("View details of a Role")
                .module(module)
                .build()
        );

        permissions.add(PermissionEntity.builder()
                .name("Role.edit")
                .title("Edit Role Details")
                .description("Edit details of a Role")
                .module(module)
                .build()
        );

        permissions.add(PermissionEntity.builder()
                .name("Role.deactivate")
                .title("Deactivate Role")
                .description("Deactivate a Role")
                .module(module)
                .build()
        );

        permissions.add(PermissionEntity.builder()
                .name("Role.activate")
                .title("Activate Role")
                .description("Activate a Role")
                .module(module)
                .build()
        );

        try {
            permissionRepository.saveAll(permissions);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
