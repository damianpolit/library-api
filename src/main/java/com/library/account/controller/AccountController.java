package com.library.account.controller;

import com.library.account.entity.Account;
import com.library.account.service.AddNewAccount;
import com.library.account.service.GetAccount;
import com.library.account.service.RemoveAccount;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/accounts")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:8080")
final class AccountController {
    
    private final AddNewAccount addNewAccount;
    private final GetAccount getAccount;
    private final RemoveAccount removeAccount;
    
    @PostMapping
    public ResponseEntity<Account> add(@RequestBody Account account) {
        return new ResponseEntity<>(addNewAccount.execute(account), HttpStatus.CREATED);
    }
    
    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getById(@PathVariable Long accountId) {
        return new ResponseEntity<>(getAccount.getById(accountId), HttpStatus.ACCEPTED);
    }
    
    @GetMapping
    public ResponseEntity<List<Account>> getList() {
        return new ResponseEntity<>(getAccount.getAll(), HttpStatus.ACCEPTED);
    }
    
    @DeleteMapping("/{accountId}")
    public ResponseEntity<Account> remove(@PathVariable Long accountId) {
        removeAccount.execute(accountId);
        return new ResponseEntity<>(HttpStatus.GONE);
    }
}
