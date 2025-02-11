package com.fintech.transaction_service.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDTO {

    @NotBlank(message = "By User ID is required")
    private String byUserId;

    @NotBlank(message = "To User ID is required")
    private String toUserId;

    @NotBlank(message = "By Wallet ID is required")
    private String byWalletId;

    @NotBlank(message = "To Wallet ID is required")
    private String toWalletId;

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    private BigDecimal amount;  // toDo: Amount is stored as string for now

    // Custom Annotation to be added for check Debit/Credit
    @NotNull(message = "Transaction type is required")
    private String type;

    @NotBlank(message = "Main expense type is required")
    private String mainExpenseType;

    @NotBlank(message = "Sub expense type is required")
    private String subExpenseType;

    private String note;

    // Getters and Setters
}
