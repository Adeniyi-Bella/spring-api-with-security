package io.javatech.api.repository;

import io.javatech.api.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    //method to find an account by username
    Account findByUsername(String username);
    //Define more methods that are not in the jpa repo
}