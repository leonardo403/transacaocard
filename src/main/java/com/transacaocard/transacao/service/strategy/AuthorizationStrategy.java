package com.transacaocard.transacao.service.strategy;

import com.transacaocard.transacao.model.Account;
import com.transacaocard.transacao.model.Transaction;

public interface AuthorizationStrategy {
    String authorize(Account account, Transaction transaction);
}