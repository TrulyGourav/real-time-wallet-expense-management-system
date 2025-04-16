package com.fintech.transaction_service.controller;

import com.fintech.transaction_service.model.ElasticTransaction;
import com.fintech.transaction_service.service.AnalyticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/all")
    public List<ElasticTransaction> getAllTransactions() {
        return analyticsService.getAllTransactions();
    }

    @GetMapping("/user/{userId}")
    public List<ElasticTransaction> getUserTransactions(@PathVariable String userId) {
        return analyticsService.getTransactionsByUser(userId);
    }
}
