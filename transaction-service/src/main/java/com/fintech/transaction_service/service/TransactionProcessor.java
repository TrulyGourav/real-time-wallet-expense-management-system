package com.fintech.transaction_service.service;

import com.fintech.transaction_service.constant.TransactionType;
import com.fintech.transaction_service.dto.TransactionRequestDTO;
import com.fintech.transaction_service.dto.WalletResponseDTO;
import com.fintech.transaction_service.model.Transaction;
import com.fintech.transaction_service.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.Instant;

@Service
public class TransactionProcessor {

    private final RestTemplate restTemplate;
    private final TransactionRepository transactionRepository;

    @Value("${user.service.url}")
    private String userServiceUrl;

    public TransactionProcessor(TransactionRepository transactionRepository, RestTemplate restTemplate) {
        this.transactionRepository = transactionRepository;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public void processTransaction(TransactionRequestDTO transactionRequest) {
        String byWalletId = transactionRequest.getByWalletId();
        String toWalletId = transactionRequest.getToWalletId();
        BigDecimal amount = transactionRequest.getAmount();

        // Validate and process like before
        ResponseEntity<WalletResponseDTO> senderWalletResponse = restTemplate.getForEntity(
                userServiceUrl + "/api/wallet/" + byWalletId, WalletResponseDTO.class);
        if (!senderWalletResponse.hasBody()) throw new RuntimeException("Sender wallet not found");

        ResponseEntity<WalletResponseDTO> receiverWalletResponse = restTemplate.getForEntity(
                userServiceUrl + "/api/wallet/" + toWalletId, WalletResponseDTO.class);
        if (!receiverWalletResponse.hasBody()) throw new RuntimeException("Receiver wallet not found");

        restTemplate.put(userServiceUrl + "/api/wallet/debit?userID="
                + senderWalletResponse.getBody().getUserId()
                + "&amount=" + amount, null);

        restTemplate.put(userServiceUrl + "/api/wallets/credit?walletId="
                + toWalletId + "&amount=" + amount, null);

        Transaction transaction = new Transaction(transactionRequest.getByUserId(), transactionRequest.getToUserId(),
                byWalletId, toWalletId, amount, TransactionType.DEBIT,
                transactionRequest.getMainExpenseType(), transactionRequest.getSubExpenseType(),
                Instant.now(), transactionRequest.getNote());

        transactionRepository.save(transaction);
    }
}
