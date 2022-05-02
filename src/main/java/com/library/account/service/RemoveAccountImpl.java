package com.library.account.service;

import com.library.account.repository.AccountRepository;
import com.library.account.service.RemoveAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
final class RemoveAccountImpl implements RemoveAccount {
    
    private final AccountRepository accountRepository;
    
    @Override
    public void execute(Long id) {
        accountRepository.deleteById(id);
    }
    
}
