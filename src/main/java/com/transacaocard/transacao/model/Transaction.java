package com.transacaocard.transacao.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Transaction {
    public Transaction(String id, String accountId, double amount, String merchant, String mcc) {
        //TODO Auto-generated constructor stub
    }
    private String id;
    private String accountId;
    private double amount;
    private String merchant;
    private String mcc;
}
