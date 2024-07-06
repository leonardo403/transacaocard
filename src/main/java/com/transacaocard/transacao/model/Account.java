package com.transacaocard.transacao.model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class Account {
    private String id;
    private double foodBalance;
    private double mealBalance;
    private double cashBalance;
}
