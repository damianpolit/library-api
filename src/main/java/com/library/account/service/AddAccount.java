package com.library.account.service;

import com.library.account.entity.Account;
import com.library.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddAccount {
    
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    
    public Account execute(final Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }
}
