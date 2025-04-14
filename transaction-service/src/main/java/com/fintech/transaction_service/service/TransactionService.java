package com.fintech.transaction_service.service;

import com.fintech.transaction_service.constant.TransactionType;
import com.fintech.transaction_service.dto.TransactionRequestDTO;
import com.fintech.transaction_service.dto.WalletResponseDTO;
import com.fintech.transaction_service.model.Transaction;
import com.fintech.transaction_service.repository.TransactionRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Value("${user.service.url}")  // Inject User Service Base URL from properties
    private String userServiceUrl;

//    private final RestTemplate restTemplate;

    private final TransactionRepository transactionRepository;

    private final TransactionProducer transactionProducer;

    public TransactionService(TransactionRepository transactionRepository, TransactionProducer transactionProducer) {
        this.transactionRepository = transactionRepository;
        this.transactionProducer = transactionProducer;
    }


//    @Transactional
//    public Transaction createTransaction(TransactionRequestDTO transactionRequest) {
//        String byWalletId = transactionRequest.getByWalletId();
//        String toWalletId = transactionRequest.getToWalletId();
//        BigDecimal amount = transactionRequest.getAmount();
//
//        // Fetch Sender Wallet (Ensure it exists)
//        ResponseEntity<WalletResponseDTO> senderWalletResponse = restTemplate.getForEntity(
//                userServiceUrl + "/api/wallet/" + byWalletId, WalletResponseDTO.class);
//
//        if (!senderWalletResponse.hasBody()) {
//            throw new RuntimeException("Sender wallet not found");
//        }
//
//        // Fetch Receiver Wallet
//        ResponseEntity<WalletResponseDTO> receiverWalletResponse = restTemplate.getForEntity(
//                userServiceUrl + "/api/wallet/" + toWalletId, WalletResponseDTO.class);
//
//        if (!receiverWalletResponse.hasBody()) {
//            throw new RuntimeException("Receiver wallet not found");
//        }
//
//        // Debit Sender Wallet
//        restTemplate.put(userServiceUrl + "/api/wallet/debit?userID="
//                + senderWalletResponse.getBody().getUserId()
//                + "&amount=" + amount, null);
//
//        // Credit Receiver Wallet
//        restTemplate.put(userServiceUrl + "/api/wallets/credit?walletId="
//                + toWalletId + "&amount="
//                + amount, null);
//
//        // Create Transaction Log
//        Transaction transaction = new Transaction(transactionRequest.getByUserId(), transactionRequest.getToUserId(),
//                byWalletId, toWalletId, amount, TransactionType.DEBIT,
//                transactionRequest.getMainExpenseType(), transactionRequest.getSubExpenseType(),
//                Instant.now(), transactionRequest.getNote());
//
//        transactionRepository.save(transaction);
//
//        return transaction;
//    }

    @Transactional
    public Transaction createTransaction(TransactionRequestDTO transactionRequest) {
        transactionProducer.sendTransaction(transactionRequest);
        return null; // optional: return dummy or async ID
    }

    public Transaction getTransactionById(String id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public void deleteTransaction(String id) {
        if (!transactionRepository.existsById(id)) {
            throw new RuntimeException("Transaction not found with id: " + id);
        }
        transactionRepository.deleteById(id);
    }
}
