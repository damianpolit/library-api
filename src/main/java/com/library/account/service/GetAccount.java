package com.library.account.service;

import com.library.account.entity.Account;

import java.util.List;

public interface GetAccount {
    
    List<Account> getAll();
    
    Account getById(Long accountId);
}
