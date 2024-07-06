package com.transacaocard.transacao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public String authorizeTransaction(@RequestBody Transaction transaction) {
        return transactionService.authorizeTransaction(transaction);
    }
}