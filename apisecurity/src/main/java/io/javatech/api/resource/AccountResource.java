package io.javatech.api.resource;

import io.javatech.api.model.Account;
import io.javatech.api.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.javatech.api.resource.EmployeeResource.getLocation;

@RestController
@RequestMapping(path="/api/accounts")
@RequiredArgsConstructor
public class AccountResource {
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account){
        Account newAccount = accountService.createAccount(account);
        return ResponseEntity.created(getLocation(newAccount.getId().intValue())).body(newAccount);
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAccount(){
        return ResponseEntity.ok(accountService.getAccounts());
    }
}
