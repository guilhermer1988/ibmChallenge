package com.ibmchallengejava.ibmchallenge.service;

import com.ibmchallengejava.ibmchallenge.model.Account;
import com.ibmchallengejava.ibmchallenge.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> findAll() {
        return accountRepository.findAll();
    }
    public Account findById(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        return account.orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public Account findByNumber(double number) {
        Optional<Account> account = Optional.ofNullable(accountRepository.findByAccountNumber(number));
        return account.orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public void insert(Account account) {
        accountRepository.save(account);
    }

    public void delete(Account account) {
        try {
            accountRepository.delete(account);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException("Account not found");
        }
    }
}