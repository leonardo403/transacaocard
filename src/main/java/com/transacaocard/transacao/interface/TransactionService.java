package com.transacaocard.transacao.interface;

import com.example.transactionauthorizer.model.Transaction;

public interface TransactionService {

    public interface AuthorizationService {
        String authorizeTransaction(Transaction transaction);
    }
}
