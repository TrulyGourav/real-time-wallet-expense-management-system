package com.fintech.user_wallet_service.util;

import com.fintech.user_wallet_service.constant.Role;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {
    public Role convertToRoleEnum(String value){
        Role role;
        try {
            role = Role.valueOf(value.toUpperCase()); // Converts string to uppercase and then enum
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + value);
        }
        return role;
    }
}
