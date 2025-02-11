package com.fintech.transaction_service.service;

import com.fintech.transaction_service.constant.TransactionType;
import com.fintech.transaction_service.dto.TransactionRequestDTO;
import com.fintech.transaction_service.model.Transaction;
import com.fintech.transaction_service.repository.TransactionRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction createTransaction(TransactionRequestDTO transactionRequest) {
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(transactionRequest, transaction);
        System.out.println("Lombok check: "+transactionRequest.getMainExpenseType()); //check

        transaction.setType(TransactionType.valueOf(transactionRequest.getType()));
        transaction.setTransactionDate(Instant.now()); // Setting transaction date at the service layer
        return transactionRepository.save(transaction);
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
