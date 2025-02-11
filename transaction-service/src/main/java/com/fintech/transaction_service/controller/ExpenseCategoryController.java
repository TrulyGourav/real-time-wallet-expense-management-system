package com.fintech.transaction_service.controller;

import com.fintech.transaction_service.dto.ExpenseCategoryRequest;
import com.fintech.transaction_service.model.ExpenseCategory;
import com.fintech.transaction_service.service.ExpenseCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expense-category")
public class ExpenseCategoryController {

    @Autowired
    private ExpenseCategoryService expenseCategoryService;

    @PostMapping
    public ResponseEntity<ExpenseCategory> addCategory(@Valid @RequestBody ExpenseCategoryRequest expenseCategoryRequest) {
        ExpenseCategory createdCategory = expenseCategoryService.addCategory(expenseCategoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCategory);
    }

    @GetMapping
    public ResponseEntity<List<ExpenseCategory>> getAllCategories() {
        List<ExpenseCategory> categories = expenseCategoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseCategory> getCategoryById(@PathVariable String id) {
        ExpenseCategory category = expenseCategoryService.getCategoryById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
        return ResponseEntity.ok(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseCategory> updateCategory(@Valid @PathVariable String id, @RequestBody ExpenseCategoryRequest expenseCategoryRequest) {
        ExpenseCategory updatedCategory = expenseCategoryService.updateCategory(id, expenseCategoryRequest);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        expenseCategoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
