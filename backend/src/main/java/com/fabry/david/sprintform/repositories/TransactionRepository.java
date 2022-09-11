package com.fabry.david.sprintform.repositories;

import com.fabry.david.sprintform.domains.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
    public Page<Transaction> findAll(Pageable pageable);

    public Optional<Transaction> findById(String id);

    public Page<Transaction> findBySummaryLike(String summary, Pageable pageable);

    public Transaction findFirstByOrderBySumAsc();

    public Transaction findFirstByOrderBySumDesc();

    public Transaction findFirstByOrderByPaidAsc();

    public Transaction findFirstByOrderByPaidDesc();
}
