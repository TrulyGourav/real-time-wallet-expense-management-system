package com.fintech.user_wallet_service.service;

import com.fintech.user_wallet_service.dto.LoginRequest;
import com.fintech.user_wallet_service.dto.RegisterRequest;
import com.fintech.user_wallet_service.model.User;
import com.fintech.user_wallet_service.repository.UserRepository;
import com.fintech.user_wallet_service.util.JwtUtil;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String registerUser(RegisterRequest request) {
        Optional<User> isEmailExists = userRepository.findByEmail(request.getEmail());
        Optional<User> isUsernameExists = userRepository.findByUsername(request.getUsername());
        if(isEmailExists.isPresent()){
            return "User Already Exists with the email";
        }
        if(isUsernameExists.isPresent()){
            return "This username is not available";
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);
        return "User registered successfully";
    }

    public String loginUser(LoginRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.getEmail());
        if (userOptional.isPresent() &&
                passwordEncoder.matches(request.getPassword(), userOptional.get().getPassword())) {
            return jwtUtil.generateToken(userOptional.get().getEmail());
        }
        throw new RuntimeException("Invalid credentials");
    }
}
