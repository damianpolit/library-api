package com.library.account.service.impl;

import com.library.account.entity.Account;
import com.library.account.repository.AccountRepository;
import com.library.account.service.GetAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
final class GetAccountImpl implements GetAccount {
    
    private final AccountRepository accountRepository;
    
    @Override
    public Account getById(final Long accountId) {
        return accountRepository.getById(accountId);
    }
    
    @Override
    public List<Account> getAll() {
        return accountRepository.findAll();
    }
}
