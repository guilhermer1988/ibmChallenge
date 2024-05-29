package com.ibmchallengejava.ibmchallenge.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="Transaction")
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "account_origen")
    private Account accountOrigin;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "account_destiny")
    private Account accountDestiny;

    @Setter
    @Getter
    private Double value;

    @Setter
    @Getter
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createAt;

    public Transaction() {
    }

    public Transaction(Account accountOrigin, Account accountDestiny, Double value) {
        this.accountOrigin = accountOrigin;
        double totalValueOrigin = this.accountOrigin.getAccountBalance();
        totalValueOrigin = totalValueOrigin - value;

        if (value <= 0) {
            throw new IllegalArgumentException("Value transfer invalid");
        }

        if (totalValueOrigin < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        this.accountOrigin.setAccountBalance(totalValueOrigin);

        this.accountDestiny = accountDestiny;
        double totalValueDestiny = this.accountDestiny.getAccountBalance();
        totalValueDestiny = totalValueDestiny + value;
        this.accountOrigin.setAccountBalance(totalValueOrigin);

        this.value = value;
        this.createAt = LocalDateTime.now();
    }

}
