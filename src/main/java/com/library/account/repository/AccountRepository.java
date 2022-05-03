package com.library.account.repository;

import com.library.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountRepository extends JpaRepository<Account, Long> {
    
    @Query(nativeQuery = true, value = "SELECT * FROM Account WHERE login = ?1 LIMIT 1")
            Account findByLogin(String login);
}
