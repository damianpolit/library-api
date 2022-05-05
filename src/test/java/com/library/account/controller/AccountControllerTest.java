package com.library.account.controller;

import com.library.account.entity.Account;
import com.library.account.service.AddAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.library.account.factory.AccountCreator.admin;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
class AccountControllerTest {
    
    
    @MockBean
    private AddAccount addAccount;
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void should() throws Exception {
        Account account = admin();
        
        when(addAccount.execute(account)).thenReturn(account);
        
        mockMvc.perform(post("api/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"testthat\": \"test that\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string(HttpHeaders.LOCATION, "http://localhost/api/accounts"));
    }
}