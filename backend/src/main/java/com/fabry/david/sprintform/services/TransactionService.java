package com.fabry.david.sprintform.services;

import com.fabry.david.sprintform.domains.Transaction;
import com.fabry.david.sprintform.helpers.TransactionSearchInput;
import com.fabry.david.sprintform.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.*;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public List<Transaction> findAllTransaction() {
        return transactionRepository.findAll();
    }

    public Optional<Transaction> findTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public List<Transaction> findTransactionBySummary(String summary) {
        return transactionRepository.findBySummaryLike(summary);
    }

    public List<Transaction> searchTransaction(TransactionSearchInput transactionSearchInput) {
        System.out.println(transactionSearchInput.getPaidStart());

        Transaction t = new Transaction();
        t.setSummary(transactionSearchInput.getSummary());
        t.setCategory(transactionSearchInput.getCategory());

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withMatcher("summary", contains())
                .withMatcher("category", exact());
        Example<Transaction> exampleQuery = Example.of(t, matcher);

        List<Transaction> transactions = transactionRepository.findAll(exampleQuery);

        if (transactionSearchInput.getSumMin() != null) {
            transactions = transactions
                    .stream()
                    .filter(transaction -> transaction.sum >= transactionSearchInput.getSumMin())
                    .collect(Collectors.toList());
        }
        if (transactionSearchInput.getSumMax() != null) {
            transactions = transactions
                    .stream()
                    .filter(transaction -> transaction.sum <= transactionSearchInput.getSumMax())
                    .collect(Collectors.toList());
        }

        if (transactionSearchInput.getPaidStart() != null) {
            transactions = transactions
                    .stream()
                    .filter(transaction -> transaction.paid.getTime() >= transactionSearchInput.getPaidStart().getTime())
                    .collect(Collectors.toList());
        }
        if (transactionSearchInput.getPaidEnd() != null) {
            transactions = transactions
                    .stream()
                    .filter(transaction -> transaction.paid.getTime() <= transactionSearchInput.getPaidEnd().getTime())
                    .collect(Collectors.toList());
        }

        return transactions;
    }
}
