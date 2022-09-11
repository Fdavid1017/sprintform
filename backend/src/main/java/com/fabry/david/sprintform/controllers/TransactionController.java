package com.fabry.david.sprintform.controllers;

import com.fabry.david.sprintform.domains.Transaction;
import com.fabry.david.sprintform.enums.TransactionCategory;
import com.fabry.david.sprintform.helpers.TransactionInput;
import com.fabry.david.sprintform.helpers.TransactionRanges;
import com.fabry.david.sprintform.helpers.TransactionSearchInput;
import com.fabry.david.sprintform.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    // GET

    @GetMapping(path = "")
    public Page<Transaction> getAllTransaction(Pageable pageable) {
        return transactionService.findAllTransaction(pageable);
    }

    @GetMapping(path = "/{id}")
    public Optional<Transaction> getTransactionById(@PathVariable("id") String id) {
        return transactionService.findTransactionById(id);
    }

    @GetMapping(path = "/summary/{summary}")
    public Page<Transaction> getTransactionBySummary(@PathVariable("summary") String summary, Pageable pageable) {
        return transactionService.findTransactionBySummary(summary, pageable);
    }

    @GetMapping(path = "/search")
    public Page<Transaction> searchTransactions(
            Pageable pageable,
            @RequestParam(required = false) String summary,
            @RequestParam(required = false) TransactionCategory category,
            @RequestParam(required = false) Float sumMin,
            @RequestParam(required = false) Float sumMax,
            @RequestParam(required = false) Optional<Long> paidStart,
            @RequestParam(required = false) Optional<Long> paidEnd
    ) {
        TransactionSearchInput transactionSearchInput = new TransactionSearchInput(summary, category, sumMin, sumMax, null, null);

        paidStart.ifPresent(aLong -> transactionSearchInput.setPaidStart(new Date(aLong)));
        paidEnd.ifPresent(aLong -> transactionSearchInput.setPaidEnd(new Date(aLong)));

        System.out.println(transactionSearchInput.getPaidStart());
        System.out.println(transactionSearchInput.getPaidEnd());

        return transactionService.searchTransaction(transactionSearchInput, pageable);
    }

    @GetMapping(path = "/ranges")
    public TransactionRanges getRanges() {
        return transactionService.getRanges();
    }

    // POST

    @PostMapping(path = "")
    public Transaction createTransaction(@RequestBody TransactionInput transactionInput) {
        return transactionService.createTransaction(transactionInput);
    }

    // PUT

    @PutMapping(path = "/{id}")
    public Transaction editTransaction(@PathVariable("id") String id, @RequestBody TransactionInput transactionInput) {
        return transactionService.editTransaction(id, transactionInput);
    }

    // DELETE

    @DeleteMapping(path = "/{id}")
    public void removeTransaction(@PathVariable("id") String id) {
        transactionService.removeTransaction(id);
    }
}
