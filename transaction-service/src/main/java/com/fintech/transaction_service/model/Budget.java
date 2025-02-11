package com.fintech.transaction_service.model;

import com.fintech.transaction_service.constant.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

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
}
