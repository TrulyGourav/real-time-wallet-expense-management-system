package com.fintech.transaction_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class WalletResponseDTO {

    private String id;
    private String userId;
    private BigDecimal balance;

}
