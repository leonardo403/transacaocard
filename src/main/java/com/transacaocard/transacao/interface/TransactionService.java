package com.transacaocard.transacao.interface;

import com.transacaocard.transacao.model.Transaction;

public interface TransactionService {

    public interface AuthorizationService {
        String authorizeTransaction(Transaction transaction);
    }
}
