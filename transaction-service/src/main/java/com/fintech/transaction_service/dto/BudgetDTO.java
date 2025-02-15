package com.fintech.transaction_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BudgetDTO {

    @NotBlank(message = "User ID is required")
    private String userId;

    @PositiveOrZero(message = "Housing budget must be zero or positive")
    private BigDecimal housingBudget = BigDecimal.ZERO;

    @PositiveOrZero(message = "Utilities budget must be zero or positive")
    private BigDecimal utilities = BigDecimal.ZERO;

    @PositiveOrZero(message = "Transportation budget must be zero or positive")
    private BigDecimal transportation = BigDecimal.ZERO;

    @PositiveOrZero(message = "Grocery & Food budget must be zero or positive")
    private BigDecimal groceryFoodBudget = BigDecimal.ZERO;

    @PositiveOrZero(message = "Dining & Entertainment budget must be zero or positive")
    private BigDecimal diningEntertainmentBudget = BigDecimal.ZERO;

    @PositiveOrZero(message = "Fitness & Wellness budget must be zero or positive")
    private BigDecimal fitnessWellnessBudget = BigDecimal.ZERO;

    @PositiveOrZero(message = "Personal Care budget must be zero or positive")
    private BigDecimal personalCareBudget = BigDecimal.ZERO;

    @PositiveOrZero(message = "Health & Medical budget must be zero or positive")
    private BigDecimal healthMedicalBudget = BigDecimal.ZERO;

    @PositiveOrZero(message = "Insurance budget must be zero or positive")
    private BigDecimal insuranceBudget = BigDecimal.ZERO;

    @PositiveOrZero(message = "Education budget must be zero or positive")
    private BigDecimal educationBudget = BigDecimal.ZERO;

    @PositiveOrZero(message = "Childcare & Parenting budget must be zero or positive")
    private BigDecimal childcareParentingBudget = BigDecimal.ZERO;

    @PositiveOrZero(message = "Pets budget must be zero or positive")
    private BigDecimal petsBudget = BigDecimal.ZERO;

    @PositiveOrZero(message = "Travel & Vacations budget must be zero or positive")
    private BigDecimal travelVacationsBudget = BigDecimal.ZERO;

    @PositiveOrZero(message = "Shopping & Apparel budget must be zero or positive")
    private BigDecimal shoppingApparelBudget = BigDecimal.ZERO;

    @PositiveOrZero(message = "Gifts & Donations budget must be zero or positive")
    private BigDecimal giftsDonationsBudget = BigDecimal.ZERO;

    @PositiveOrZero(message = "Investments & Savings budget must be zero or positive")
    private BigDecimal investmentsSavingsBudget = BigDecimal.ZERO;

    @PositiveOrZero(message = "Debts & Payments budget must be zero or positive")
    private BigDecimal debtsPaymentsBudget = BigDecimal.ZERO;

    @PositiveOrZero(message = "Miscellaneous budget must be zero or positive")
    private BigDecimal miscellaneousBudget = BigDecimal.ZERO;
}
