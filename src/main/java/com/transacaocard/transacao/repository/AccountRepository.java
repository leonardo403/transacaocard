package com.transacaocard.transacao.repository;

import com.transacaocard.transacao.model.Account;
import java.util.HashMap;
import java.util.Map;

public class AccountRepository {
    
    @Autowired
    private static Map<String, Account> accounts = new HashMap<>();

    /**
     * contas para testes
     */
    static {
        accounts.put("123", new Account("123", 200.00, 150.00, 500.00));
    }

    public Account findAccountById(String accountId) {
        return accounts.get(accountId);
    }

    public void updateAccount(Account account) {
        accounts.put(account.getId(), account);
    }
}