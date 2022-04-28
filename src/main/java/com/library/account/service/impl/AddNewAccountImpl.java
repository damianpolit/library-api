package com.library.account.service.impl;

import com.library.account.entity.Account;
import com.library.account.repository.AccountRepository;
import com.library.account.service.AddNewAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
final class AddNewAccountImpl implements AddNewAccount {
    
    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    
    @Override
    public Account execute(final Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }
}
