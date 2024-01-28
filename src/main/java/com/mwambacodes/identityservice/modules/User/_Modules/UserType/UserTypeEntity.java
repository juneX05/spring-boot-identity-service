package com.mwambacodes.identityservice.modules.User._Modules.UserType;

import com.mwambacodes.identityservice.modules.Role.RoleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "user_types",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "name_unique_key",
                        columnNames = "name"
                ),
                @UniqueConstraint(
                        name = "uuid_unique_key",
                        columnNames = "uuid"
                ),
        }
)
public class UserTypeEntity {
    public static final Long DEVELOPER = 1L;
    public static final Long SUPER_ADMIN = 2L;
    public static final Long ADMIN = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    @Column(name = "color", nullable = true)
    @ColumnDefault("'black'")
    private String color;

    @Column(name = "uuid", nullable = false)
    private UUID uuid;
}
