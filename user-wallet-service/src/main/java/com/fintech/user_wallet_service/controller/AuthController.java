package com.fintech.user_wallet_service.controller;

import com.fintech.user_wallet_service.dto.LoginRequest;
import com.fintech.user_wallet_service.dto.LoginResponse;
import com.fintech.user_wallet_service.dto.RegisterRequest;
import com.fintech.user_wallet_service.dto.GenericResponse;
import com.fintech.user_wallet_service.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/")
    public String home(){
        return "Hello Home";
    }

    @PostMapping("/register")
    public ResponseEntity<GenericResponse<Map<String, Object>>> register(@Valid @RequestBody RegisterRequest request) {
        authService.registerUser(request);
        return ResponseEntity.ok(new GenericResponse("User registered successfully", 201));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(new LoginResponse("Login Successful", authService.loginUser(request), 200));
    }
}
