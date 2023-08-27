package io.javatech.api.service.impl;

import io.javatech.api.model.Account;
import io.javatech.api.model.Role;
import io.javatech.api.repository.AccountRepository;
import io.javatech.api.repository.RoleRepository;
import io.javatech.api.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository; //fetch role to assign to user
    private final PasswordEncoder encoder; //already defined in security to return a new encrypted password

    @Override
    public Account createAccount(Account account) {
        account.setPassword(encoder.encode(account.getPassword())); //overide the raw password from the user
        Role role= roleRepository.findByName("ROLE_USER"); //give a role user when some creates account
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        account.setRoles(roles); //set the roles on the account
        return accountRepository.save(account);
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }
}
