package com.fintech.user_wallet_service.dto;

import lombok.Data;

import java.util.Map;

@Data
public class GenericResponse<T extends Map<String, ?>> {
    private String message;
    private int statusCode;
    private T data;

    public GenericResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public GenericResponse(String message, int statusCode, T data) {
        this.message = message;
        this.statusCode = statusCode;
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
