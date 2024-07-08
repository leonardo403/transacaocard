package com.transacaocard.transacao.service;

import com.transacaocard.transacao.model.Transaction;
import com.transacaocard.transacao.model.Account;
import com.transacaocard.transacao.repository.AccountRepository;
import com.transacaocard.transacao.service.strategy.AuthorizationStrategy;
import com.transacaocard.transacao.service.strategy.FoodAuthorizationStrategy;
import com.transacaocard.transacao.service.strategy.MealAuthorizationStrategy;
import com.transacaocard.transacao.service.strategy.CashAuthorizationStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TransactionService{

    private final AccountRepository accountRepository;

    private final Map<String, AuthorizationStrategy> strategyMap;

    @Autowired
    public TransactionService(AccountRepository accountRepository, 
                              FoodAuthorizationStrategy foodStrategy,
                              MealAuthorizationStrategy mealStrategy,
                              CashAuthorizationStrategy cashStrategy) {
        this.accountRepository = accountRepository;
        this.strategyMap = new HashMap<>();
        this.strategyMap.put("5411", foodStrategy);
        this.strategyMap.put("5412", foodStrategy);
        this.strategyMap.put("5811", mealStrategy);
        this.strategyMap.put("5812", mealStrategy);
        this.strategyMap.put("default", cashStrategy);
    }

    public String authorizeTransaction(Transaction transaction) {
        Account account = accountRepository.findAccountById(transaction.getAccountId());
        if (account == null) {
            return "{\"code\": \"07\"}";
        }

        String mcc = transaction.getMcc();
        String merchant = transaction.getMerchant();

        /**
         * MCC baseado no nome do comerciante
         */
        if (merchant.contains("UBER EATS")) {
            mcc = "5812";
        }

        AuthorizationStrategy strategy = strategyMap.getOrDefault(mcc, strategyMap.get("default"));
        String result = strategy.authorize(account, transaction);
        if (result != null) {
            accountRepository.updateAccount(account);
            return result;
        }
        return "{\"code\": \"51\"}";
    }
}
