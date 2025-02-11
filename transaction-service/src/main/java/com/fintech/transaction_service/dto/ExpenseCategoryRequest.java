package com.fintech.transaction_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class ExpenseCategoryRequest {

    @NotBlank(message = "Main category is required")
    private String mainCategory;

    @NotEmpty(message = "Sub-categories list cannot be empty")
    private List<@NotBlank(message = "Sub-category cannot be blank") String> subCategories;

    public ExpenseCategoryRequest() {
    }

    public ExpenseCategoryRequest(String mainCategory, List<@NotBlank(message = "Sub-category cannot be blank.") String> subCategories) {
        this.mainCategory = mainCategory;
        this.subCategories = subCategories;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public List<String> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<String> subCategories) {
        this.subCategories = subCategories;
    }
}
