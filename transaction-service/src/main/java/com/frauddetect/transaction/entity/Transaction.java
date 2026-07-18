package com.frauddetect.transaction.entity;

import com.fraudDetect.enums.Currency;
import com.fraudDetect.enums.PaymentMethod;
import com.fraudDetect.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;
import org.apache.kafka.common.protocol.types.Field;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(
        name = "transactions",
        indexes = {
                @Index(name = "idx_transaction_id", columnList = "transactionId", unique = true),
                @Index(name = "idx_user_id", columnList = "userId"),
                @Index(name = "idx_status", columnList = "status"),
                @Index(name = "idx_created_at", columnList = "created_at")
        }
)
@Getter
@Setter
@NoArgsConstructor
public class Transaction extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "transaction_id", nullable = false, unique = true)
    private UUID transactionId;
    @Column(name = "user_id", nullable = false)
    private UUID userId;
    @Column(name = "merchant_id", nullable = false)
    private UUID merchantId;
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 3)
    private Currency currency;
    @Column(name = "merchant_name", nullable = false)
    private String merchantName;
    @Column(name = "merchant_category", nullable = false)
    private String merchantCategory;
    @Column(nullable = false)
    private String country;
    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status;

}
