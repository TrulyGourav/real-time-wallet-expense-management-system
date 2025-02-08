package com.fintech.user_wallet_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ResponseMessage {
    private String message;
    private int statusCode;

    public ResponseMessage(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
