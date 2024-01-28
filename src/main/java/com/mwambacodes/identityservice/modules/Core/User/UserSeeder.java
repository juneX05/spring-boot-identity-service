package com.mwambacodes.identityservice.modules.Core.User;

import com.mwambacodes.identityservice.modules.Core.User._Modules.UserRole.UserRoleRepository;
import com.mwambacodes.identityservice.modules.Core.Permission.PermissionEntity;
import com.mwambacodes.identityservice.modules.Core.Permission.PermissionRepository;
import com.mwambacodes.identityservice.modules.Core.Role.RoleEntity;
import com.mwambacodes.identityservice.modules.Core.Role.RoleRepository;
import com.mwambacodes.identityservice.modules.Core.User._Modules.UserRole.UserRoleEntity;
import com.mwambacodes.identityservice.modules.Core.User._Modules.UserRole.UserRoleKey;
import com.mwambacodes.identityservice.modules.Core.User._Modules.UserStatus.UserStatusEntity;
import com.mwambacodes.identityservice.modules.Core.User._Modules.UserStatus.UserStatusRepository;
import com.mwambacodes.identityservice.modules.Core.User._Modules.UserType.UserTypeEntity;
import com.mwambacodes.identityservice.modules.Core.User._Modules.UserType.UserTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserSeeder {
    private final PasswordEncoder passwordEncoder;
    private final PermissionRepository permissionRepository;
    private final UserStatusRepository userStatusRepository;
    private final UserTypeRepository userTypeRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public void run() {
        System.out.println(UserSeeder.class + ": Start Seeding.");

        seedPermissions();
        seedData();

        System.out.println(UserSeeder.class + ": Done running seeders.");

    }

    public void seedData() {;

        try {
            RoleEntity developerRole = roleRepository.findById(1L).stream().toList().get(0);

            UserEntity user = new UserEntity();
            user.setFirstName("Developer");
            user.setLastName("Joe");
            user.setEmail("developer@gmail.com");
            user.setPassword(passwordEncoder.encode("password"));
            user.setCreatedAt(Date.from(Instant.now()));
            user.setUpdatedAt(Date.from(Instant.now()));
            user.setUserStatus(userStatusRepository.findById(Integer.valueOf(UserStatusEntity.ACTIVE)).get());
            user.setUserType(userTypeRepository.findById(UserTypeEntity.DEVELOPER).get());
            var model = userRepository.save(user);

            UserRoleKey userRoleKey = new UserRoleKey();
            userRoleKey.setUserId(model.getId());
            userRoleKey.setRoleId(developerRole.getId());

            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setId(userRoleKey);
            userRoleEntity.setRole(developerRole);
            userRoleEntity.setUser(model);
            userRoleRepository.save(userRoleEntity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void seedPermissions() {
        List<PermissionEntity> permissions = new ArrayList<>();
        var module = "User";

        permissions.add(PermissionEntity.builder()
                .name("User.list")
                .title("List Users")
                .description("List All Users")
                .module(module)
                .build()
        );

        permissions.add(PermissionEntity.builder()
                .name("User.create")
                .title("Create User")
                .description("Create a User")
                .module(module)
                .build()
        );

        permissions.add(PermissionEntity.builder()
                .name("User.view")
                .title("View User Details")
                .description("View details of a User")
                .module(module)
                .build()
        );

        permissions.add(PermissionEntity.builder()
                .name("User.edit")
                .title("Edit User Details")
                .description("Edit details of a User")
                .module(module)
                .build()
        );

        permissions.add(PermissionEntity.builder()
                .name("User.deactivate")
                .title("Deactivate User")
                .description("Deactivate a User")
                .module(module)
                .build()
        );

        permissions.add(PermissionEntity.builder()
                .name("User.activate")
                .title("Activate User")
                .description("Activate a User")
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
