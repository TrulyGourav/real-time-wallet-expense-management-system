package com.fintech.transaction_service.constant;

public enum TransactionType {
    CREDIT("Credit"),
    DEBIT("Debit");

    private final String displayName;

    // Constructor to associate presentable string with enum
    TransactionType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
