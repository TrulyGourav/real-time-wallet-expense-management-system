package com.fintech.user_wallet_service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class LoginResponse {
    private String message;
    private String jwtToken;
    private int statusCode;

    public LoginResponse(String message, String jwtToken, int statusCode) {
        this.message = message;
        this.jwtToken = jwtToken;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}

