package com.fintech.transaction_service.model;

import com.fintech.transaction_service.constant.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "budgets")
public class Budget {

    @Id
    private String id;

    private String userId;

    private BigDecimal housingBudget;

    private BigDecimal utilities;

    private BigDecimal transportation;

    private BigDecimal groceryFoodBudget;

    private BigDecimal diningEntertainmentBudget;

    private BigDecimal fitnessWellnessBudget;

    private BigDecimal personalCareBudget;

    private BigDecimal healthMedicalBudget;

    private BigDecimal insuranceBudget;

    private BigDecimal educationBudget;

    private BigDecimal childcareParentingBudget;

    private BigDecimal petsBudget;

    private BigDecimal travelVacationsBudget;

    private BigDecimal shoppingApparelBudget;

    private BigDecimal giftsDonationsBudget;

    private BigDecimal investmentsSavingsBudget;

    private BigDecimal debtsPaymentsBudget;

    private BigDecimal miscellaneousBudget;

    private TransactionType type;

    @CreatedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Instant createdAt;

    @LastModifiedDate
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Instant updatedAt;

}
