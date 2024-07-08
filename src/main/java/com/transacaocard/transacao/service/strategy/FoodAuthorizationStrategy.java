package com.transacaocard.transacao.service.strategy;

import com.transacaocard.transacao.model.Account;
import com.transacaocard.transacao.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class FoodAuthorizationStrategy implements AuthorizationStrategy {

    @Override
    public String authorize(Account account, Transaction transaction) {
        double amount = transaction.getAmount();
        if (account.getFoodBalance() >= amount) {
            account.setFoodBalance(account.getFoodBalance() - amount);
            return "{\"code\": \"00\"}";
        }
        return null;
    }
}