package com.library.account.factory;

import com.library.account.entity.Account;
import com.library.account.entity.Role;

import java.time.LocalDateTime;
import java.util.Random;

public class AccountCreator {
    
    public static Account member() {
        final Random random = new Random();
        return Account.builder()
                .id(random.nextLong(1000))
                .login("membermember")
                .password("memberassword")
                .createdAt(LocalDateTime.of(2021, 1, 1, 10, 1))
                .role(Role.MEMBER)
                .build();
    }
    
    public static Account admin() {
        final Random random = new Random();
        return Account.builder()
                .id(random.nextLong(1000))
                .login("adminadmin")
                .password("adminpassword")
                .createdAt(LocalDateTime.of(2021, 1, 1, 10, 1))
                .role(Role.ADMIN)
                .build();
    }
}
