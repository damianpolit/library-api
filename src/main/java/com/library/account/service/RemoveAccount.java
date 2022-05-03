package com.library.account.service;

import com.library.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoveAccount {
    
    private final AccountRepository accountRepository;
    
    public void execute(Long id) {
        accountRepository.deleteById(id);
    }
    
}
