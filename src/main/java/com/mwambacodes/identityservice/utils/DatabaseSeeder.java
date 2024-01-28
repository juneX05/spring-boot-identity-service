package com.mwambacodes.identityservice.utils;

import com.mwambacodes.identityservice.modules.System.Domain.DomainSeeder;
import com.mwambacodes.identityservice.modules.System.Domain._Modules.DomainStatus.DomainStatusSeeder;
import com.mwambacodes.identityservice.modules.Core.Permission.PermissionEntity;
import com.mwambacodes.identityservice.modules.Core.Permission.PermissionRepository;
import com.mwambacodes.identityservice.modules.Core.Permission.PermissionSeeder;
import com.mwambacodes.identityservice.modules.Core.Role.RoleEntity;
import com.mwambacodes.identityservice.modules.Core.Role.RoleRepository;
import com.mwambacodes.identityservice.modules.Core.Role.RoleSeeder;
import com.mwambacodes.identityservice.modules.Core.Status.StatusSeeder;
import com.mwambacodes.identityservice.modules.Core.User.UserSeeder;
import com.mwambacodes.identityservice.modules.Core.User._Modules.UserStatus.UserStatusSeeder;
import com.mwambacodes.identityservice.modules.Core.User._Modules.UserType.UserTypeSeeder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder {
    private final PermissionSeeder permissionSeeder;
    private final DomainStatusSeeder domainStatusSeeder;
    private final DomainSeeder domainSeeder;
    private final RoleSeeder roleSeeder;
    private final StatusSeeder statusSeeder;
    private final UserStatusSeeder userStatusSeeder;
    private final UserSeeder userSeeder;
    private final UserTypeSeeder userTypeSeeder;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    private boolean alreadySetup = false;

    @EventListener
    @Transactional
    public void seed(
            ContextRefreshedEvent event
    ) {
        if(alreadySetup) return;

        permissionSeeder.run();
        domainStatusSeeder.run();
        domainSeeder.run();
        statusSeeder.run();
        userStatusSeeder.run();
        roleSeeder.run();
        userTypeSeeder.run();
        userSeeder.run();

        finalizeSeeding();

    }

    public void finalizeSeeding() {
        RoleEntity roleEntity = roleRepository.findById(1L).get();
        List<PermissionEntity> permissions = permissionRepository.findAll().stream().toList();
        roleEntity.setPermissions(new HashSet<>(permissions));

        try {
            roleRepository.save(roleEntity);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        alreadySetup = true;
    }

}
