package com.ibmchallengejava.ibmchallenge.repository;

import com.ibmchallengejava.ibmchallenge.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountNumber(double accountNumber);
}
