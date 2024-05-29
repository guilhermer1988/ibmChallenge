package com.ibmchallengejava.ibmchallenge.controller;

import com.ibmchallengejava.ibmchallenge.model.Account;
import com.ibmchallengejava.ibmchallenge.model.Client;
import com.ibmchallengejava.ibmchallenge.model.Dtos.TransactionRequest;
import com.ibmchallengejava.ibmchallenge.model.Transaction;
import com.ibmchallengejava.ibmchallenge.repository.AccountRepository;
import com.ibmchallengejava.ibmchallenge.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TransactionController {
    private TransactionService _transactionService;

    public TransactionController(TransactionService transactionService) {
        this._transactionService = transactionService;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/Transaction/{accountNumber}")
    public ResponseEntity<List<Transaction>> getTransactionByNumber(@PathVariable double accountNumber) {
        List<Transaction> transactions = _transactionService.getExtractByAccountNumber(accountNumber);
        return ResponseEntity.ok(transactions);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/Transaction")
    public ResponseEntity<TransactionRequest> createTransaction(@Valid @RequestBody TransactionRequest newTransaction) {
        TransactionRequest newTransactiontResult = _transactionService.transaction(newTransaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTransactiontResult);
    }
}
