package com.ibmchallengejava.ibmchallenge.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name="Account")
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    // Getters e Setters
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private double accountNumber;

    @Getter
    @Setter
    private double accountBalance;

    @Getter
    @Setter
    @OneToOne
    @JsonIgnoreProperties("Account")
    private Client client;

    public Account() {
    }

    public Account(Long id, double number, double balance) {
        super();
        this.id = id;
        this.accountNumber = number;
        this.accountBalance = balance;
    }
}
