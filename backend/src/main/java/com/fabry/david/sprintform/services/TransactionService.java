package com.fabry.david.sprintform.services;

import com.fabry.david.sprintform.domains.Transaction;
import com.fabry.david.sprintform.helpers.TransactionInput;
import com.fabry.david.sprintform.helpers.TransactionSearchInput;
import com.fabry.david.sprintform.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.*;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    private final ModelMapper modelMapper;

    public Page<Transaction> findAllTransaction(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

    public Optional<Transaction> findTransactionById(BigInteger id) {
        return transactionRepository.findById(id);
    }

    public Page<Transaction> findTransactionBySummary(String summary, Pageable pageable) {
        return transactionRepository.findBySummaryLike(summary, pageable);
    }

    public Page<Transaction> searchTransaction(TransactionSearchInput transactionSearchInput, Pageable pageable) {
        Transaction t = new Transaction();
        t.setSummary(transactionSearchInput.getSummary());
        t.setCategory(transactionSearchInput.getCategory());

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withMatcher("summary", contains())
                .withMatcher("category", exact());
        Example<Transaction> exampleQuery = Example.of(t, matcher);

        return transactionRepository.findAll(exampleQuery, pageable);

//        if (transactionSearchInput.getSumMin() != null) {
//            transactions = transactions
//                    .stream()
//                    .filter(transaction -> transaction.sum >= transactionSearchInput.getSumMin())
//                    .collect(Collectors.toList());
//        }
//        if (transactionSearchInput.getSumMax() != null) {
//            transactions = transactions
//                    .stream()
//                    .filter(transaction -> transaction.sum <= transactionSearchInput.getSumMax())
//                    .collect(Collectors.toList());
//        }
//
//        if (transactionSearchInput.getPaidStart() != null) {
//            transactions = transactions
//                    .stream()
//                    .filter(transaction -> transaction.paid.getTime() >= transactionSearchInput.getPaidStart().getTime())
//                    .collect(Collectors.toList());
//        }
//        if (transactionSearchInput.getPaidEnd() != null) {
//            transactions = transactions
//                    .stream()
//                    .filter(transaction -> transaction.paid.getTime() <= transactionSearchInput.getPaidEnd().getTime())
//                    .collect(Collectors.toList());
//        }

//        return transactions;
    }

    public Transaction createTransaction(TransactionInput transactionInput) {
        Transaction transaction = modelMapper.map(transactionInput, Transaction.class);
        transaction.setPaid(new Date());
        transactionRepository.save(transaction);

        return transaction;
    }

    public Transaction editTransaction(BigInteger id, TransactionInput transactionInput) {
        Optional<Transaction> transaction = findTransactionById(id);

        if (transaction.isPresent()) {
            Transaction t = transaction.get();
            t.setSummary(transactionInput.getSummary());
            t.setCategory(transactionInput.getCategory());
            t.setSum(transactionInput.getSum());
            t.setCurrency(transactionInput.getCurrency());

            transactionRepository.save(t);

            return t;
        }

        return null;
    }

    public void removeTransaction(BigInteger id) {
        Optional<Transaction> transaction = findTransactionById(id);

        transaction.ifPresent(transactionRepository::delete);
    }
}
