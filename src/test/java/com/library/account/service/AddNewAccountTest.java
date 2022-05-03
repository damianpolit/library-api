package com.library.account.service;

import com.library.LibraryApplication;
import com.library.account.entity.Account;
import com.library.account.repository.AccountRepository;
import com.library.testcontainers.config.ContainerEnvironment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static com.library.account.factory.AccountCreator.admin;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest(classes = LibraryApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AddNewAccountTest extends ContainerEnvironment {
    
    
    @Autowired
    private AccountRepository accountRepository;
    
    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
    }
    
    @Test
    @DisplayName("Return empty Account list")
    void shouldReturnEmptyAccountList() {
        // given & when
        List<Account> accountList = accountRepository.findAll();
        
        // then
        assertEquals(0, accountList.size());
    }
    
    @Test
    @DisplayName("Return not empty Account list")
    void shouldReturnNotEmptyAccountList() {
        // given & when
        accountRepository.save(admin());
        List<Account> accountList = accountRepository.findAll();
        
        // then
        assertEquals(1, accountList.size());
    }
    
    @Test
    @DisplayName("Return saved Account by login")
    void shouldFetchSavedAccountByLogin() {
        // given & when
        final Account savedAccount = accountRepository.save(admin());
        final Account fetchedAccount = accountRepository.findByLogin(savedAccount.getLogin());
        
        // then
        assertEquals(savedAccount.getLogin(), fetchedAccount.getLogin());
    }
}