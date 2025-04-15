package com.fintech.transaction_service.repository;

import com.fintech.transaction_service.model.ElasticTransaction;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElasticTransactionRepository extends ElasticsearchRepository<ElasticTransaction, String> {

}
