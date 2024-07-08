package com.transacaocard.transacao.service.strategy;

import com.transacaocard.transacao.model.Account;
import com.transacaocard.transacao.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class MealAuthorizationStrategy implements AuthorizationStrategy {

    @Override
    public String authorize(Account account, Transaction transaction) {
        double amount = transaction.getAmount();
        if (account.getMealBalance() >= amount) {
            account.setMealBalance(account.getMealBalance() - amount);
            return "{\"code\": \"00\"}";
        }
        return null;
    }
}