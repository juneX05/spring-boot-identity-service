package com.mwambacodes.identityservice.modules.Core.Media;

import com.mwambacodes.identityservice.modules.Core.User.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "media",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uuid_unique_key",
                        columnNames = "uuid"
                )
        }
)
public class MediaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Long id;

    @Column(nullable = false, name = "original_name")
    private String originalName;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "extension")
    private String extension;

    @Column(nullable = false, name = "size")
    private Double size;

    @Column(nullable = false, name = "uploaded_date")
    private LocalDateTime uploadedDate;

    @ManyToOne
    @JoinColumn(nullable = false, name = "uploaded_by")
    private UserEntity uploadedBy;

    @Column(nullable = false, name = "uuid")
    private UUID uuid;

}
