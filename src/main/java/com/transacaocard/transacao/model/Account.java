package com.transacaocard.transacao.model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class Account {
    public Account(String id, double foodBalance, double mealBalance, double cashBalance) {
        //TODO Auto-generated constructor stub
    }
    private String id;
    private double foodBalance;
    private double mealBalance;
    private double cashBalance;
}
