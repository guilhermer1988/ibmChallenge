package com.ibmchallengejava.ibmchallenge.service;

import com.ibmchallengejava.ibmchallenge.model.Account;
import com.ibmchallengejava.ibmchallenge.model.Client;
import com.ibmchallengejava.ibmchallenge.model.Dtos.TransactionRequest;
import com.ibmchallengejava.ibmchallenge.model.Transaction;
import com.ibmchallengejava.ibmchallenge.repository.AccountRepository;
import com.ibmchallengejava.ibmchallenge.repository.ClientRepository;
import com.ibmchallengejava.ibmchallenge.repository.TransactionRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;


    public List<Transaction> getExtractByAccountNumber(double accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber);
        return transactionRepository.findByAccountOrigin(account);
    }
    public List<Transaction> getReportByDate(LocalDate startDate, LocalDate endDate) {
        LocalDateTime start = startDate.atTime(00, 00, 00);
        LocalDateTime end = endDate.atTime(23, 59, 59);
        return transactionRepository.findByCreateAtBetween(start, end);
    }

    public TransactionRequest transaction(TransactionRequest transacaoRequest) {

        Account origin = accountRepository.findByAccountNumber(transacaoRequest.getAccountNumberOrigin());
        Account destiny = accountRepository.findByAccountNumber(transacaoRequest.getAccountNumberDestination());
        Transaction transaction = new Transaction(origin, destiny, transacaoRequest.getValue());

        accountRepository.save(origin);
        accountRepository.save(destiny);
        transactionRepository.save(transaction);

        return transacaoRequest;
    }
}
