package com.frauddetect.fraud.service;

import com.frauddetect.fraud.entity.KnownDevice;
import com.frauddetect.fraud.repository.KnownDeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KnownDeviceService implements IKnownDeviceService {
    private final KnownDeviceRepository knownDeviceRepository;

    @Override
    public boolean isNewDevice(UUID userId, String deviceId) {
        return !knownDeviceRepository.existsByUserIdAndDeviceId(
                userId,
                deviceId
        );
    }

    @Override
    public void registerDevice(UUID userId, String deviceId) {
        if (!knownDeviceRepository.existsByUserIdAndDeviceId(userId, deviceId)) {

            KnownDevice device = KnownDevice.builder()
                    .userId(userId)
                    .deviceId(deviceId)
                    .firstSeen(Instant.now())
                    .build();

            knownDeviceRepository.save(device);
        }
    }
}
