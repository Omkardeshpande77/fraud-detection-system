package com.frauddetect.fraud.feature;

import com.frauddetect.events.TransactionCreatedEvent;
import com.frauddetect.fraud.dto.request.MlPredictionRequest;
import com.frauddetect.fraud.service.ITransactionVelocityService;
import com.frauddetect.fraud.service.KnownDeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionFeatureBuilder {
    private final KnownDeviceService knownDeviceService;
    private final ITransactionVelocityService transactionVelocityService;

    public MlPredictionRequest build(TransactionCreatedEvent event) {
        boolean isNewDevice =
                knownDeviceService.isNewDevice(
                        event.userId(),
                        event.deviceId()
                );
        return MlPredictionRequest.builder()
                .amount(event.amount().doubleValue())
                .country(event.country())
                .merchantCategory(event.merchantCategory())
                .paymentMethod(event.paymentMethod().name())

                // Temporary values
                .isNewDevice(isNewDevice)
                .transactionsLastHour(
                        transactionVelocityService.getTransactionsLastHour(
                                event.userId()
                        )
                )

                .build();
    }
}
