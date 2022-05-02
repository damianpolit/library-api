package com.library.account.factory.impl;

import com.library.account.entity.Account;
import com.library.account.entity.Role;
import com.library.account.factory.AccountFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AccountCreator implements AccountFactory {
    
    @Override
    public Account createMember() {
        final Random random = new Random();
        return Account.builder()
                .id(random.nextLong(1000))
                .login("testLogin")
                .password("testPassword")
                .createdAt(LocalDateTime.of(2021, 1, 1, 10, 1))
                .role(Role.MEMBER)
                .build();
    }
    
    @Override
    public Account createAdmin() {
        final Random random = new Random();
        return Account.builder()
                .id(random.nextLong(1000))
                .login("testLogin")
                .password("testPassword")
                .createdAt(LocalDateTime.of(2021, 1, 1, 10, 1))
                .role(Role.ADMIN)
                .build();
    }
    
    @Override
    public List<Account> getListWithAdminAndMember(final Account admin, final Account member) {
        
        List<Account> accountList = new ArrayList<>();
        accountList.add(admin);
        accountList.add(member);
        
        return accountList;
    }
}
