package com.fintech.user_wallet_service.controller;

import com.fintech.user_wallet_service.model.Wallet;
import com.fintech.user_wallet_service.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public String deposit(@RequestParam Long userId, @RequestParam double amount) {
        walletService.deposit(userId, amount);
        return "Amount successfully deposited!";
    }

    @GetMapping("/balance/{userId}")
    public double getBalance(@PathVariable Long userId) {
        return walletService.getBalance(userId);
    }
}
