package com.fintech.user_wallet_service.controller;

import com.fintech.user_wallet_service.dto.GenericResponse;
import com.fintech.user_wallet_service.dto.LoginResponse;
import com.fintech.user_wallet_service.model.Wallet;
import com.fintech.user_wallet_service.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/")
    public String home(){
        return "Hello to Wallet Controller";
    }

    @PostMapping("/deposit")
    public ResponseEntity<GenericResponse<Map<String, Object>>> deposit(@RequestParam Long userId, @RequestParam double amount) {
        walletService.deposit(userId, amount);
        return ResponseEntity.ok(new GenericResponse("Amount Deposited Successfully", 200));
    }

    @GetMapping("/balance/{userId}")
    public ResponseEntity<GenericResponse<Map<String, Object>>> getBalance(@PathVariable Long userId) {
        double balance =  walletService.getBalance(userId);

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("balance", balance);
        return ResponseEntity.ok(new GenericResponse("Balance Fetched Successfully", 200, dataMap));
    }
}
