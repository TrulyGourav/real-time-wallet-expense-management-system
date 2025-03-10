package com.fintech.user_wallet_service.repository;

import com.fintech.user_wallet_service.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, String> {
    Optional<Wallet> findByUser_Id(String userId);
}
