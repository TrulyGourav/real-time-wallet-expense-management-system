package com.fintech.user_wallet_service.security;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
