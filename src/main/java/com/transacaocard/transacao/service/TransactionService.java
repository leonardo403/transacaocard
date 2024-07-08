package com.transacaocard.transacao.service;

import com.transacaocard.transacao.model.Transaction;
import org.springframework.stereotype.Service;


@Service
public class TransactionService{

    private final AccountRepository accountRepository;

    public TransactionService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public String authorizeTransaction(Transaction transaction) {
        Account account = accountRepository.findAccountById(transaction.getAccountId());
        if (account == null) {
            return "{\"code\": \"07\"}";
        }

        double amount = transaction.getAmount();
        String mcc = transaction.getMcc();
        String merchant = transaction.getMerchant();
        
        /**
         * substituição de MCC baseado no nome do comerciante
         */
        if (merchant.contains("UBER EATS")) {
            mcc = "5812";
        }

        if ((mcc.equals("5411") || mcc.equals("5412")) && account.getFoodBalance() >= amount) {
            account.setFoodBalance(account.getFoodBalance() - amount);
        } else if ((mcc.equals("5811") || mcc.equals("5812")) && account.getMealBalance() >= amount) {
            account.setMealBalance(account.getMealBalance() - amount);
        } else if (account.getCashBalance() >= amount) {
            account.setCashBalance(account.getCashBalance() - amount);
        } else {
            return "{\"code\": \"51\"}";
        }

        accountRepository.updateAccount(account);
        return "{\"code\": \"00\"}";
    }
}
