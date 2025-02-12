package com.fintech.transaction_service.service;

import com.fintech.transaction_service.dto.ExpenseCategoryRequest;
import com.fintech.transaction_service.model.ExpenseCategory;
import com.fintech.transaction_service.repository.ExpenseCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseCategoryService {

    @Autowired
    private ExpenseCategoryRepository expenseCategoryRepository;

    public ExpenseCategory addCategory(ExpenseCategoryRequest expenseCategoryRequest) {
        ExpenseCategory expenseCategoryToSave = new ExpenseCategory();

        expenseCategoryToSave.setMainCategory(expenseCategoryRequest.getMainCategory());
        expenseCategoryToSave.setSubCategories(expenseCategoryRequest.getSubCategories());

        return expenseCategoryRepository.save(expenseCategoryToSave);
    }

    public List<ExpenseCategory> getAllCategories() {
        return expenseCategoryRepository.findAll();
    }

    public ExpenseCategory getCategoryById(String id) {
        return expenseCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    public ExpenseCategory updateCategory(String id, ExpenseCategoryRequest expenseCategoryRequest) {
        return expenseCategoryRepository.findById(id).map(category -> {
            category.setMainCategory(expenseCategoryRequest.getMainCategory());
            category.setSubCategories(expenseCategoryRequest.getSubCategories());
            return expenseCategoryRepository.save(category);
        }).orElseThrow(() -> new RuntimeException("Expense Category not found with id: " + id));
    }

    public void deleteCategory(String id) {
        expenseCategoryRepository.deleteById(id);
    }
}

