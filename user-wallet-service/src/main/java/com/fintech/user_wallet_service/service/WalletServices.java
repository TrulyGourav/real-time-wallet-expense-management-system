package com.fintech.user_wallet_service.service;

import com.fintech.user_wallet_service.model.Wallet;
import com.fintech.user_wallet_service.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletService {

    private final WalletRepository walletRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Transactional
    public void deposit(Long userId, double amount) {
        Wallet wallet = walletRepository.findById(userId).orElse(new Wallet(userId, 0));
        wallet.setBalance(wallet.getBalance() + amount);
        walletRepository.save(wallet);
    }

    public double getBalance(Long userId) {
        return walletRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"))
                .getBalance();
    }
}
