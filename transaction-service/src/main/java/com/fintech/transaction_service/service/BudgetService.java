package com.fintech.transaction_service.service;

import com.fintech.transaction_service.dto.BudgetDTO;
import com.fintech.transaction_service.model.Budget;
import com.fintech.transaction_service.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.Instant;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    public Budget createOrUpdateBudget(BudgetDTO budgetDTO) {
        Budget budget = budgetRepository.findByUserId(budgetDTO.getUserId())
                .orElse(new Budget());

        // Update values, set to zero if null
        budget.setUserId(budgetDTO.getUserId());
        budget.setHousingBudget(getSafeValue(budgetDTO.getHousingBudget()));
        budget.setUtilities(getSafeValue(budgetDTO.getUtilities()));
        budget.setTransportation(getSafeValue(budgetDTO.getTransportation()));
        budget.setGroceryFoodBudget(getSafeValue(budgetDTO.getGroceryFoodBudget()));
        budget.setDiningEntertainmentBudget(getSafeValue(budgetDTO.getDiningEntertainmentBudget()));
        budget.setFitnessWellnessBudget(getSafeValue(budgetDTO.getFitnessWellnessBudget()));
        budget.setPersonalCareBudget(getSafeValue(budgetDTO.getPersonalCareBudget()));
        budget.setHealthMedicalBudget(getSafeValue(budgetDTO.getHealthMedicalBudget()));
        budget.setInsuranceBudget(getSafeValue(budgetDTO.getInsuranceBudget()));
        budget.setEducationBudget(getSafeValue(budgetDTO.getEducationBudget()));
        budget.setChildcareParentingBudget(getSafeValue(budgetDTO.getChildcareParentingBudget()));
        budget.setPetsBudget(getSafeValue(budgetDTO.getPetsBudget()));
        budget.setTravelVacationsBudget(getSafeValue(budgetDTO.getTravelVacationsBudget()));
        budget.setShoppingApparelBudget(getSafeValue(budgetDTO.getShoppingApparelBudget()));
        budget.setGiftsDonationsBudget(getSafeValue(budgetDTO.getGiftsDonationsBudget()));
        budget.setInvestmentsSavingsBudget(getSafeValue(budgetDTO.getInvestmentsSavingsBudget()));
        budget.setDebtsPaymentsBudget(getSafeValue(budgetDTO.getDebtsPaymentsBudget()));
        budget.setMiscellaneousBudget(getSafeValue(budgetDTO.getMiscellaneousBudget()));

        budget.setUpdatedAt(Instant.now());

        return budgetRepository.save(budget);
    }

    public Budget getBudgetByUserId(String userId) {
        return budgetRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Budget not found for user ID: " + userId));
    }

    private BigDecimal getSafeValue(BigDecimal value) {
        return (value != null) ? value : BigDecimal.ZERO;
    }
}
