package com.fabry.david.sprintform.controllers;

import com.fabry.david.sprintform.domains.Transaction;
import com.fabry.david.sprintform.helpers.TransactionInput;
import com.fabry.david.sprintform.helpers.TransactionSearchInput;
import com.fabry.david.sprintform.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    // GET

    @GetMapping(path = "")
    public List<Transaction> getAllTransaction() {
        return transactionService.findAllTransaction();
    }

    @GetMapping(path = "/{id}")
    public Optional<Transaction> getTransactionById(@PathVariable("id") BigInteger id) {
        return transactionService.findTransactionById(id);
    }

    @GetMapping(path = "/summary/{summary}")
    public List<Transaction> getTransactionBySummary(@PathVariable("summary") String summary) {
        return transactionService.findTransactionBySummary(summary);
    }

    @GetMapping(path = "/search")
    public List<Transaction> searchTransactions(@RequestBody TransactionSearchInput transactionSearchInput) {
        return transactionService.searchTransaction(transactionSearchInput);
    }

    // POST

    @PostMapping(path = "")
    public Transaction createTransaction(@RequestBody TransactionInput transactionInput) {
        return transactionService.createTransaction(transactionInput);
    }

    // PUT

    @PutMapping(path = "/{id}")
    public Transaction editTransaction(@PathVariable("id") BigInteger id, @RequestBody TransactionInput transactionInput) {
        return transactionService.editTransaction(id, transactionInput);
    }

    // DELETE

    @DeleteMapping(path = "/{id}")
    public void removeTransaction(@PathVariable("id") BigInteger id) {
        transactionService.removeTransaction(id);
    }
}
