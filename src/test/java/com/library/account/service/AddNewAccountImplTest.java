package com.library.account.service;

import com.library.account.entity.Account;
import com.library.account.factory.impl.AccountCreator;
import com.library.account.repository.AccountRepository;
import com.library.testcontainers.config.ContainerEnvironment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AddNewAccountImplTest extends ContainerEnvironment {
    
    
    @Autowired
    private AccountRepository accountRepository;
    private Account account;
    
    @BeforeEach
    void setUp() {
    account = new AccountCreator().createAdmin();
    }
    
    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
    }
    
    @Test
    @DisplayName("Should return empty Account list")
    void shouldReturnEmptyAccountList() {
        // given
        List<Account> accountList = accountRepository.findAll();
        
        // when & then
        assertEquals(0, accountList.size());
    }
    
    @Test
    @DisplayName("Should return not empty Account list")
    void shouldReturnNotEmptyAccountList() {
        // given
        accountRepository.save(account);
        
        // when
        List<Account> accountList = accountRepository.findAll();
        
        // then
        assertEquals(1, accountList.size());
    }
    
    @Test
    @DisplayName("Should return saved Account by login")
    void shouldFetchSavedAccountByLogin() {
        // given
        accountRepository.save(account);
        
        // when
        final List<Account> fetchedAccount = accountRepository.findByLogin(account.getLogin());
        
        // then
        assertEquals("testLogin", fetchedAccount.stream().findFirst().get().getLogin());
    }
}