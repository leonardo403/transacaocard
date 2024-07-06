package com.transacaocard.transacao.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Transaction {
    private String id;
    private String accountId;
    private double amount;
    private String merchant;
    private String mcc;
}
