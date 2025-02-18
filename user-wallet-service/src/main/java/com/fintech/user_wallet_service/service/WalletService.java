package com.fintech.user_wallet_service.service;

import com.fintech.user_wallet_service.model.User;
import com.fintech.user_wallet_service.model.Wallet;
import com.fintech.user_wallet_service.repository.UserRepository;
import com.fintech.user_wallet_service.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class WalletService {

    private final WalletRepository walletRepository;
    private final UserRepository userRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository, UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void deposit(String userId, double amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Wallet wallet = walletRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Wallet for userId "+userId+" not found"));

        wallet.setBalance(wallet.getBalance() + amount);
        walletRepository.save(wallet);
    }

    public Wallet getWalletByUserId(String userId) {
        return walletRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
    }

    public double getBalance(String userId) {
        return walletRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"))
                .getBalance();
    }

    public void createWallet(User user, double balance) {
        String randomWalletId = UUID.randomUUID().toString();
        Wallet wallet = new Wallet(randomWalletId, user, balance);
        walletRepository.save(wallet);
    }
}
