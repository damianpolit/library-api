package com.library.account.service;

import com.library.account.entity.Account;
import com.library.account.entity.Role;
import com.library.account.repository.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
final class GetAccountImplTest {
    
    @Mock
    private AccountRepository accountRepository;
    
    @Autowired
    @InjectMocks
    private GetAccountImpl getAccount;
    
    private Account account1;
    private Account account2;
    private List<Account> accountList;
    
    @BeforeEach
    void setUp() {
        accountList = new ArrayList<>();
        
        account1 = Account.builder()
                .id(1L)
                .login("theLogin")
                .password("thePassword")
                .createdAt(LocalDateTime.now())
                .role(Role.ADMIN)
                .build();
        
        account2 = Account.builder()
                .id(3L)
                .login("testLogin")
                .password("testPassword")
                .createdAt(LocalDateTime.now())
                .role(Role.MEMBER)
                .build();
        
        accountList.add(account1);
        accountList.add(account2);
    }
    
    @AfterEach
    void tearDown() {
        account1 = account2 = null;
        accountList = null;
    }
    
    @Test
    void shouldReturnListOfAllAccounts() {
        //when
        accountRepository.save(account1);
        when(accountRepository.findAll()).thenReturn(accountList);
        
        List<Account> underTestList = getAccount.getAll();
        
        //then
        assertEquals(underTestList, accountList);
        verify(accountRepository, times(1)).save(account1);
        verify(accountRepository, times(1)).findAll();
    }
    
    @Test
    void shouldReturnAccountById() {
        //given & when
        accountRepository.save(account1);
        accountRepository.save(account2);
        
        when(accountRepository.findById(account1.getId())).thenReturn(Optional.ofNullable(account1));
        when(accountRepository.findById(account2.getId())).thenReturn(Optional.ofNullable(account2));
        
        accountRepository.findById(account1.getId());
        accountRepository.findById(account2.getId());
        
        //then
        verify(accountRepository, times(2)).save(any());
        verify(accountRepository, times(1)).findById(account1.getId());
        verify(accountRepository, times(1)).findById(account2.getId());
        assertEquals("testLogin", account2.getLogin());
        assertEquals("thePassword", account1.getPassword());
    }
}