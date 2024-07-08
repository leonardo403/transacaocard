package com.transacaocard.transacao.service.strategy;

import com.transacaocard.transacao.model.Account;
import com.transacaocard.transacao.model.Transaction;
import org.springframework.stereotype.Component;

@Component
public class CashAuthorizationStrategy implements AuthorizationStrategy {

    @Override
    public String authorize(Account account, Transaction transaction) {
        double amount = transaction.getAmount();
        if (account.getCashBalance() >= amount) {
            account.setCashBalance(account.getCashBalance() - amount);
            return "{\"code\": \"00\"}";
        }
        return "{\"code\": \"51\"}";
    }
}