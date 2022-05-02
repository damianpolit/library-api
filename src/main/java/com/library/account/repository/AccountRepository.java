package com.library.account.repository;

import com.library.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    
    @Query(value = "SELECT a FROM Account a WHERE a.login = ?1")
    List<Account> findByLogin(String login);
}
