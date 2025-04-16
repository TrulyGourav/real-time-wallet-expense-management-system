package com.fintech.transaction_service.service;

import com.fintech.transaction_service.model.ElasticTransaction;
import com.fintech.transaction_service.repository.ElasticTransactionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnalyticsService {

    private final ElasticTransactionRepository elasticTransactionRepository;

    public AnalyticsService(ElasticTransactionRepository repository) {
        this.elasticTransactionRepository = repository;
    }

    public List<ElasticTransaction> getAllTransactions() {
        List<ElasticTransaction> list = new ArrayList<>();
        elasticTransactionRepository.findAll().forEach(list::add);
        return list;
    }

    public List<ElasticTransaction> getTransactionsByUser(String userId) {
        return getAllTransactions().stream()
                .filter(txn -> txn.getByUserId().equals(userId) || txn.getToUserId().equals(userId))
                .toList();
    }
}