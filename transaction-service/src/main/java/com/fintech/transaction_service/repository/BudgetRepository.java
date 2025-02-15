package com.fintech.transaction_service.repository;

import com.fintech.transaction_service.model.Budget;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BudgetRepository extends MongoRepository<Budget, String> {
    Optional<Budget> findByUserId(String userId);
}
