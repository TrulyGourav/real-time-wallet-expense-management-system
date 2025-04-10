package com.fintech.transaction_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.transaction_service.dto.TransactionRequestDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TransactionConsumer {

    private final TransactionProcessor transactionProcessor;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public TransactionConsumer(TransactionProcessor transactionProcessor) {
        this.transactionProcessor = transactionProcessor;
    }

    @KafkaListener(topics = "${wallet.kafka.topic.transactions}", groupId = "wallet-group")
    public void listen(String message) {
        try {
            TransactionRequestDTO request = objectMapper.readValue(message, TransactionRequestDTO.class);
            transactionProcessor.processTransaction(request);
        } catch (Exception e) {
//            Now we have choices:
//            1. Compensate the transaction (rollback debit)
//            2. Retry (Kafka retries processing)
//            3. Send to Dead Letter Queue and alert admin for manual check
            System.err.println("Failed to process transaction from Kafka: " + e.getMessage());
        }
    }
}
