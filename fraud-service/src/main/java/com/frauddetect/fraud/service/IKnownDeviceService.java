package com.frauddetect.fraud.service;

import java.util.UUID;

public interface IKnownDeviceService {
    boolean isNewDevice(UUID userId, String deviceId);

    void registerDevice(UUID userId, String deviceId);
}
