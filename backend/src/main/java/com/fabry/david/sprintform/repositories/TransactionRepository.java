package com.fabry.david.sprintform.repositories;

import com.fabry.david.sprintform.domains.Transaction;
import com.fabry.david.sprintform.enums.TransactionCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends MongoRepository<Transaction, Long> {
    public List<Transaction> findAll();

    public Optional<Transaction> findById(Long id);

    public List<Transaction> findBySummaryLike(String summary);
}
