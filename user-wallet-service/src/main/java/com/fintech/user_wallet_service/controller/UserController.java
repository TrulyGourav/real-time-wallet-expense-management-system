package com.fintech.user_wallet_service.controller;

import com.fintech.user_wallet_service.dto.GenericResponse;
import com.fintech.user_wallet_service.model.User;
import com.fintech.user_wallet_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<Map<String, Object>>> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("user", user);
        return ResponseEntity.ok(new GenericResponse<>("User fetched successfully", 200, dataMap));
    }

}
