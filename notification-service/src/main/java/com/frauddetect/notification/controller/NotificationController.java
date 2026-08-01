package com.frauddetect.notification.controller;


import com.frauddetect.notification.dto.response.NotificationResponse;
import com.frauddetect.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/{transactionId}")
    public List<NotificationResponse> getNotifications(
            @PathVariable UUID transactionId) {

        return notificationService.getByTransactionId(transactionId);
    }
}
