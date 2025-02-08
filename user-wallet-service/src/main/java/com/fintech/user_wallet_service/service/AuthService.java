package com.fintech.user_wallet_service.service;

import com.fintech.user_wallet_service.dto.LoginRequest;
import com.fintech.user_wallet_service.dto.RegisterRequest;
import com.fintech.user_wallet_service.exception.InvalidCredentialsException;
import com.fintech.user_wallet_service.model.User;
import com.fintech.user_wallet_service.repository.UserRepository;
import com.fintech.user_wallet_service.util.CommonUtil;
import com.fintech.user_wallet_service.util.JwtUtil;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final WalletService walletService;
    private final CommonUtil commonUtil;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, WalletService walletService, CommonUtil commonUtil, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.walletService = walletService;
        this.commonUtil = commonUtil;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public void registerUser(RegisterRequest request) {
        Optional<User> isEmailExists = userRepository.findByEmail(request.getEmail());
        Optional<User> isUsernameExists = userRepository.findByUsername(request.getUsername());
        if(isEmailExists.isPresent()){
            throw new IllegalArgumentException("Email is already in use");
        }
        if(isUsernameExists.isPresent()){
            throw new IllegalArgumentException("Username is already in use");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(commonUtil.convertToRoleEnum(request.getRole()));
        userRepository.save(user);
        walletService.createWallet(user, 0.0);
    }

    public String loginUser(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Account does not exists with this Email"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }
        return jwtUtil.generateToken(user.getEmail());
    }
}
