package com.transacaocard.transacao.repository;

import java.util.HashMap;
import java.util.Map;

public class AccountRepository {
    
    private static Map<String, Account> accounts = new HashMap<>();

    static {
        // Inicializa algumas contas para testes
        accounts.put("123", new Account("123", 200.00, 150.00, 500.00));
    }

    public Account findAccountById(String accountId) {
        return accounts.get(accountId);
    }

    public void updateAccount(Account account) {
        accounts.put(account.getId(), account);
    }

}
