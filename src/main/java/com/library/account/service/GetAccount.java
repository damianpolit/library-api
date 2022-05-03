package com.library.account.service;

import com.library.account.entity.Account;
import com.library.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAccount {
    
    private final AccountRepository accountRepository;
    
    public Account getById(final Long accountId) {
        return accountRepository.getById(accountId);
    }
    
    public List<Account> getAll() {
        return accountRepository.findAll();
    }
}
