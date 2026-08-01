package com.frauddetect.fraud.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
        name = "known_devices",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"user_id", "device_id"}
        )
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KnownDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID userId;

    @Column(nullable = false)
    private String deviceId;

    @Column(nullable = false)
    private Instant firstSeen;
}
