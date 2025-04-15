package com.fintech.transaction_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(indexName = "transactions")
public class ElasticTransaction {

    @Id
    private String id;

    private String byUserId;
    private String toUserId;

    private String byWalletId;
    private String toWalletId;

    private BigDecimal amount;
    private String type; // Use String to avoid enum issues

    private String mainExpenseType;
    private String subExpenseType;

    private Instant transactionDate;
    private String note;

    private Instant createdAt;
    private Instant updatedAt;
}