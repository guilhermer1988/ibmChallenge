package com.ibmchallengejava.ibmchallenge.controller;

import com.ibmchallengejava.ibmchallenge.model.Account;
import com.ibmchallengejava.ibmchallenge.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {
    private AccountRepository _accountRepository;

    public AccountController(AccountRepository accountRepository) {
        this._accountRepository = accountRepository;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/Account")
    public ResponseEntity<List<Account>> getAccount() {
        List<Account> accounts = _accountRepository.findAll();
        return ResponseEntity.ok(accounts);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/Account/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable long id) {
        Optional<Account> account = _accountRepository.findById(id);
        return account.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/Account")
    public ResponseEntity<Account> createAccount(@RequestBody Account newAccount) {
        Account newAccountResult = _accountRepository.save(newAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAccountResult);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/Account/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable long id, @RequestBody Account newAccount) {
        if (!_accountRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        newAccount.setId(id);
        Account newAccountResult = _accountRepository.save(newAccount);
        return ResponseEntity.ok(newAccountResult);
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/Account/{id}")
    public ResponseEntity<Object> deleteAccount(@PathVariable long id) {
        _accountRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
