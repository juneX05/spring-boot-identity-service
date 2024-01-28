package com.mwambacodes.identityservice.modules.Status;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "status",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "name_unique_key",
                        columnNames = "name"
                )
        }
)
public class StatusEntity {

    // Constants.
    public static final String ACTIVE = "1";
    public static final String PENDING = "2";
    public static final String INACTIVE = "3";

    @Id
    @Column(nullable = false, name = "id")
    private Integer id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = true, name = "color")
    @ColumnDefault("'black'")
    private String color;
}
