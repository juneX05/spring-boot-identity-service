package com.mwambacodes.identityservice.modules.Core.User.Actions.UserList;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserListDTO {

    @Builder
    @Data
    public static class UserStatus {
        private int id;
        private String name;
        private String color;
    }

    @Builder
    @Data
    public static class UserType {

        @Builder
        @Data
        public static class Role {
            private Long id;
            private String name;
        }

        private int id;
        private String name;
        private String color;
        private Role role;
    }

    @Builder
    @Data
    public static class User{
        private Long id;
        private String firstName;
        private String lastName;
    }

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private UUID uuid;
    private UserStatus userStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserType userType;
    private User lastUpdatedBy;
    private User createdBy;


}
