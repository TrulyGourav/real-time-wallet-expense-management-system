package com.fintech.transaction_service.repository;

import com.fintech.transaction_service.model.ExpenseCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseCategoryRepository extends MongoRepository<ExpenseCategory, String> {
}
