package com.transacaocard.transacao.controller;

import com.transacaocard.transacao.model.Transaction;
import com.transacaocard.transacao.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Gerencia transacoes de cartao de credito
 */
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * Endpoint para autorizar uma transação.
     * @param transaction A transação a ser autorizada.
     * @return A resposta com o código de autorização.
     */
    @PostMapping
    public String authorizeTransaction(@RequestBody Transaction transaction) {
        return transactionService.authorizeTransaction(transaction);
    }
}