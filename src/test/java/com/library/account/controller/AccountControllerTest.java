package com.library.account.controller;

import com.library.account.entity.Account;
import com.library.account.factory.impl.AccountCreator;
import com.library.account.repository.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AccountControllerTest {
    
    private Account account;
    
    @Autowired
    private AccountRepository accountRepository;
    
    
    @BeforeEach
    void setUp() {
        account = new AccountCreator().createAdmin();
    }
    
    @AfterEach
    void tearDown() {
        accountRepository.deleteAll();
    }
    
    
    @Test
    @DisplayName("Fetch account after save")
    void shouldReturnFetchedAccountAfterSave() {
        //given
        accountRepository.save(account);
        
        //when
        var fetchedAccount = accountRepository.getById(account.getId());
        
        //then
        assertEquals("theLogin", fetchedAccount.getLogin());
        assertEquals("ADMIN", fetchedAccount.getRole().name());
    }
    
    @Test
    @DisplayName("Return list of Accounts")
    void shouldReturnListOfAddedAccounts() {
        //given
        accountRepository.save(account);
        accountRepository.save(account);
        
        //when
        
        //then
        assertNotNull(accountRepository.getById(account.getId()));
        assertNotNull(accountRepository.getById(account.getId()));
        
    }
    
    @Test
    @DisplayName("Fetch Account by Id")
    void shouldReturnAccountById() {
        //given
        accountRepository.save(account);
        
        //when
        Optional<Account> optionalAccount = accountRepository.findById(account.getId());
        
        //then
        assertEquals(account.getId(), optionalAccount.get().getId());
    }
    
    @Test
    @DisplayName("Delete Account by Id")
    void shouldDeleteAccountById() {
        //given
        accountRepository.save(account);
        
        //when
        accountRepository.deleteById(account.getId());
        Optional<Account> optional = accountRepository.findById(account.getId());
        
        //then
        assertEquals(Optional.empty(), optional);
    }
}