package com.fintech.user_wallet_service.constant;


public enum Role {
    SUPER_ADMIN("Super Admin"),
    ADMIN("Admin"),
    USER("User");

    private final String displayName;

    // Constructor to associate presentable string with enum
    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
