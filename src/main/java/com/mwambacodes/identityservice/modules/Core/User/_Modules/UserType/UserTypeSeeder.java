package com.mwambacodes.identityservice.modules.Core.User._Modules.UserType;

import com.mwambacodes.identityservice.modules.Core.Permission.PermissionEntity;
import com.mwambacodes.identityservice.modules.Core.Permission.PermissionRepository;
import com.mwambacodes.identityservice.modules.Core.Role.RoleEntity;
import com.mwambacodes.identityservice.modules.Core.Role.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserTypeSeeder {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final UserTypeRepository userTypeRepository;

    public void run() {
        System.out.println(UserTypeSeeder.class + ": Start Seeding.");

        seedPermissions();
        seedData();

        System.out.println(UserTypeSeeder.class + ": Done running seeders.");

    }

    public void seedPermissions() {
        List<PermissionEntity> permissions = new ArrayList<>();
        var module = "UserType";
        var title = "User Type";
        var title_plural = "User Types";

        permissions.add(PermissionEntity.builder()
                .name(module + ".list")
                .title("List " + title_plural)
                .description("List All " + title_plural)
                .module(module)
                .build()
        );

        permissions.add(PermissionEntity.builder()
                .name(module + ".create")
                .title("Create " + title)
                .description("Create a " + title)
                .module(module)
                .build()
        );

        permissions.add(PermissionEntity.builder()
                .name(module + ".view")
                .title("View " + title + " Details")
                .description("View details of a " + title)
                .module(module)
                .build()
        );

        permissions.add(PermissionEntity.builder()
                .name(module + ".edit")
                .title("Edit "+ title +" Details")
                .description("Edit details of a " + title)
                .module(module)
                .build()
        );

        try {
            permissionRepository.saveAll(permissions);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void seedData() {
        List<UserTypeEntity> userTypes = new ArrayList<>();
        userTypes.add(UserTypeEntity.builder()
                .role(roleRepository.findById(RoleEntity.DEVELOPER).get())
                .name("Developer")
                .uuid(UUID.randomUUID())
                .build()
        );
        userTypes.add(UserTypeEntity.builder()
                .role(roleRepository.findById(RoleEntity.SUPER_ADMIN).get())
                .name("SuperAdmin")
                .uuid(UUID.randomUUID())
                .build()
        );
        userTypes.add(UserTypeEntity.builder()
                .role(roleRepository.findById(RoleEntity.ADMIN).get())
                .name("Admin")
                .uuid(UUID.randomUUID())
                .build()
        );

        try {
            userTypeRepository.saveAll(userTypes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
