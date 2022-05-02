package com.library;

import com.library.account.entity.Account;
import com.library.account.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
class LibraryApplicationTests  {
    
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer();
    
//    @Override
//    public void start() {
//        super.start();
//        System.setProperty("DB_URL", container.getJdbcUrl());
//        System.setProperty("DB_USERNAME", container.getUsername());
//        System.setProperty("DB_PASSWORD", container.getPassword());
//    }
    
//    @DynamicPropertySource
//    static void properties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", container::getJdbcUrl);
//        registry.add("spring.datasource.password", container::getPassword);
//        registry.add("spring.datasource.username", container::getUsername);
//
//    }
    
    
    @Test
    void contextLoads() {
        final Account account = new Account();
        account.setLogin("testlogin");
        account.setPassword("password123");
        
        
        accountRepository.save(account);
        
        System.out.println("Context loads!");
        
    }
    
}
