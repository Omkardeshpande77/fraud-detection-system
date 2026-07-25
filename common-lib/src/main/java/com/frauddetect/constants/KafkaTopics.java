package com.frauddetect.constants;

public final class KafkaTopics {
    private KafkaTopics() {
    }

    public static final String TRANSACTIONS_CREATED = "transactions.created";
    public static final String TRANSACTIONS_SCORED = "transactions.scored";
    public static final String TRANSACTIONS_APPROVED = "transactions.approved";

    public static final String TRANSACTIONS_FLAGGED = "transactions.flagged";

    public static final String NOTIFICATIONS = "notifications";

    public static final String ANALYTICS = "analytics";
}
