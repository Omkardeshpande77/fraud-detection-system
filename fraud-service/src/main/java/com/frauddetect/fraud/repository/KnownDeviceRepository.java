package com.frauddetect.fraud.repository;

import com.frauddetect.fraud.entity.KnownDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface KnownDeviceRepository extends JpaRepository<KnownDevice, UUID> {
    boolean existsByUserIdAndDeviceId(
            UUID userId,
            String deviceId
    );
}
