package com.fintech.user_wallet_service.controller;

import com.fintech.user_wallet_service.security.JwtRequest;
import com.fintech.user_wallet_service.security.JwtResponse;
import com.fintech.user_wallet_service.security.JwtTokenUtil;
import com.fintech.user_wallet_service.model.User;
import com.fintech.user_wallet_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

        import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody JwtRequest jwtRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Invalid credentials");
        }

        Optional<User> user = userRepository.findByUsername(jwtRequest.getUsername());
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        String token = jwtTokenUtil.generateToken(user.get().getUsername());
        return new JwtResponse(token);
    }
}
