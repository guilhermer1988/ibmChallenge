package com.ibmchallengejava.ibmchallenge.model.Dtos;

import com.ibmchallengejava.ibmchallenge.model.Transaction;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TransactionRequest {
    @Setter
    @Getter
    private double accountNumberOrigin;

    @Setter
    @Getter
    private double accountNumberDestination;

    @Setter
    @Getter
    private double value;

    public static TransactionRequest toRequest(Transaction transaction) {
        TransactionRequest request = new TransactionRequest();
        request.setAccountNumberOrigin(transaction.getAccountOrigin().getAccountNumber());
        request.setAccountNumberDestination(transaction.getAccountDestiny().getAccountNumber());
        request.setValue(transaction.getValue());
        return request;
    }
}
