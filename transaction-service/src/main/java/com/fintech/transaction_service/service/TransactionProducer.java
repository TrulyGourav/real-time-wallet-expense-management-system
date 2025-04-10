package com.fintech.transaction_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.transaction_service.dto.TransactionRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${wallet.kafka.topic.transactions}")
    private String topicName;

    private final ObjectMapper objectMapper;

    public TransactionProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = new ObjectMapper();
    }

    public void sendTransaction(TransactionRequestDTO requestDTO) {
        try {
            String message = objectMapper.writeValueAsString(requestDTO);
            kafkaTemplate.send(topicName, message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize transaction request", e);
        }
    }
}
