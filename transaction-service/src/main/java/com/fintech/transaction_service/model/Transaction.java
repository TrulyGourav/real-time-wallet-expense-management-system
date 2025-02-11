package com.fintech.transaction_service.model;

import com.fintech.transaction_service.constant.TransactionType;
import lombok.*;
import org.springframework.data.annotation.Id;
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
}
