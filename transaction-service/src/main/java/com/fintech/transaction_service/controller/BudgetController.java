package com.fintech.transaction_service.controller;

import com.fintech.transaction_service.dto.BudgetDTO;
import com.fintech.transaction_service.model.Budget;
import com.fintech.transaction_service.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/budget")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @PostMapping
    public ResponseEntity<Budget> createOrUpdateBudget(@Valid @RequestBody BudgetDTO budgetDTO) {
        Budget budget = budgetService.createOrUpdateBudget(budgetDTO);
        return ResponseEntity.ok(budget);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Budget> getBudgetByUserId(@PathVariable String userId) {
        Budget budget = budgetService.getBudgetByUserId(userId);
        return ResponseEntity.ok(budget);
    }
}
