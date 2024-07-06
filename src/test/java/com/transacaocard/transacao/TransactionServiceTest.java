package com.transacaocard.transacao;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private AccountRepository accountRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAuthorizeTransactionApproved() {
        Transaction transaction = new Transaction("1", "123", 50.00, "PADARIA DO ZE", "5811");
        Account account = new Account("123", 200.00, 150.00, 500.00);
        when(accountRepository.findAccountById("123")).thenReturn(account);

        String response = transactionService.authorizeTransaction(transaction);
        assertEquals("{\"code\": \"00\"}", response);
        assertEquals(100.00, account.getMealBalance());
    }

    @Test
    public void testAuthorizeTransactionInsufficientFunds() {
        Transaction transaction = new Transaction("2", "123", 500.00, "PADARIA DO ZE", "5811");
        Account account = new Account("123", 200.00, 150.00, 300.00);
        when(accountRepository.findAccountById("123")).thenReturn(account);

        String response = transactionService.authorizeTransaction(transaction);
        assertEquals("{\"code\": \"51\"}", response);
    }

    @Test
    public void testAuthorizeTransactionMerchantOverride() {
        Transaction transaction = new Transaction("3", "123", 50.00, "UBER EATS", "1234");
        Account account = new Account("123", 200.00, 150.00, 500.00);
        when(accountRepository.findAccountById("123")).thenReturn(account);

        String response = transactionService.authorizeTransaction(transaction);
        assertEquals("{\"code\": \"00\"}", response);
        assertEquals(100.00, account.getMealBalance());
    }
}
