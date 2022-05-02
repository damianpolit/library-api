package com.library.account.factory;

import com.library.account.entity.Account;

import java.util.List;

public interface AccountFactory {
    
    Account createMember();
    
    Account createAdmin();
    
    List<Account> getListWithAdminAndMember(Account admin, Account member);
}
