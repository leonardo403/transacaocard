package com.transacaocard.transacao;

import com.example.transactionauthorizer.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.Assert;
import org.junit.Test;
import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAuthorizeTransaction() throws Exception {
        Transaction transaction = new Transaction();
        transaction.setId("1");
        transaction.setAccountId("123");
        transaction.setAmount(new BigDecimal("100.00"));
        transaction.setMerchant("PADARIA DO ZE               SAO PAULO BR");
        transaction.setMcc("5811");

        mockMvc.perform(post("/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"account\":\"123\",\"totalAmount\":100.00,\"mcc\":\"5811\",\"merchant\":\"PADARIA DO ZE               SAO PAULO BR\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"code\":\"00\"}"));
    }
}
