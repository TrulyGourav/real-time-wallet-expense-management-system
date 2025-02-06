package com.fintech.user_wallet_service.repository;

import com.fintech.user_wallet_service.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
