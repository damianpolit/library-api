package com.library;

import com.library.account.repository.AccountRepository;
import com.library.testcontainers.config.ContainerEnvironment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LibraryApplicationTests extends ContainerEnvironment {
    
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Test
    void contextLoads() {
        System.out.println("Context loads!");
        
    }
    
}
