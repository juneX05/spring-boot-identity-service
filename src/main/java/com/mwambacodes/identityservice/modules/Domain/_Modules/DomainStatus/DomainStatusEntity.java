package com.mwambacodes.identityservice.modules.Domain._Modules.DomainStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(
        name = "domain_status",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "id_unique_key",
                        columnNames = "id"
                ),
                @UniqueConstraint(
                        name = "name_unique_key",
                        columnNames = "name"
                ),
        }
)
public class DomainStatusEntity {
    // Constants.
    public static final String ACTIVE = "1";
    public static final String PENDING = "2";
    public static final String INACTIVE = "3";

    //Schema Definitions.

    @Id
    @Column(nullable = false, name = "id")
    private int id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = true, name = "color")
    @ColumnDefault("'black'")
    private String color;

}
