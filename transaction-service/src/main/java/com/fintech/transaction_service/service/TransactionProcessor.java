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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;


import java.math.BigDecimal;
import java.time.Instant;

@Service
public class TransactionProcessor {

    private final RestTemplate restTemplate;
    private final TransactionRepository transactionRepository;

    @Value("${user.service.url}")
    private String userServiceUrl;

    @Value("${internal.auth.token}")
    private String internalAuthToken;

    public TransactionProcessor(TransactionRepository transactionRepository, RestTemplate restTemplate) {
        this.transactionRepository = transactionRepository;
        this.restTemplate = restTemplate;
    }

    @Transactional
    public void processTransaction(TransactionRequestDTO transactionRequest) {
        String byWalletId = transactionRequest.getByWalletId();
        String toWalletId = transactionRequest.getToWalletId();
        BigDecimal amount = transactionRequest.getAmount();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(internalAuthToken); // Add Authorization: Bearer <internalAuthToken>
        HttpEntity<Void> httpEntity = new HttpEntity<>(headers);

        // Fetch Sender Wallet
        ResponseEntity<WalletResponseDTO> senderWalletResponse = restTemplate.exchange(
                userServiceUrl + "/api/wallet/" + byWalletId,
                HttpMethod.GET,
                httpEntity,
                WalletResponseDTO.class
        );
        if (!senderWalletResponse.hasBody()) throw new RuntimeException("Sender wallet not found");

        // Fetch Receiver Wallet
        ResponseEntity<WalletResponseDTO> receiverWalletResponse = restTemplate.exchange(
                userServiceUrl + "/api/wallet/" + toWalletId,
                HttpMethod.GET,
                httpEntity,
                WalletResponseDTO.class
        );
        if (!receiverWalletResponse.hasBody()) throw new RuntimeException("Receiver wallet not found");

        // Debit Sender
        restTemplate.exchange(
                userServiceUrl + "/api/wallet/debit?userID=" + senderWalletResponse.getBody().getUserId()
                        + "&amount=" + amount,
                HttpMethod.PUT,
                httpEntity,
                Void.class
        );

        // Credit Receiver
        restTemplate.exchange(
                userServiceUrl + "/api/wallet/credit?userID=" + toWalletId
                        + "&amount=" + amount,
                HttpMethod.PUT,
                httpEntity,
                Void.class
        );

        Transaction transaction = new Transaction(transactionRequest.getByUserId(), transactionRequest.getToUserId(),
                byWalletId, toWalletId, amount, TransactionType.DEBIT,
                transactionRequest.getMainExpenseType(), transactionRequest.getSubExpenseType(),
                Instant.now(), transactionRequest.getNote());

        transactionRepository.save(transaction);
    }
}
