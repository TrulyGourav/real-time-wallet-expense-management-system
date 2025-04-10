package com.fintech.transaction_service.model;

import com.fintech.transaction_service.constant.TransactionType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "transactions")
public class Transaction {

    @Id
    private String id;

    private String byUserId;

    private String toUserId;

    private String byWalletId;

    private String toWalletId;

    private BigDecimal amount;

    private TransactionType type; // enum - Debit/Credit

    private String mainExpenseType;

    private String subExpenseType;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Instant transactionDate;

    private String note;

    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Instant createdAt;

    @LastModifiedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Instant updatedAt;

    public Transaction(String byUserId, String toUserId, String byWalletId, String toWalletId, BigDecimal amount, TransactionType type, String mainExpenseType, String subExpenseType, Instant transactionDate, String note) {
        this.byUserId = byUserId;
        this.toUserId = toUserId;
        this.byWalletId = byWalletId;
        this.toWalletId = toWalletId;
        this.amount = amount;
        this.type = type;
        this.mainExpenseType = mainExpenseType;
        this.subExpenseType = subExpenseType;
        this.transactionDate = transactionDate;
        this.note = note;
    }
}
