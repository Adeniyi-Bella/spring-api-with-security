package io.javatech.api.init;

import io.javatech.api.model.Role;
import io.javatech.api.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;

@Component
@RequiredArgsConstructor

//class adds data to DB once application is fired. This is necessary
// to create a new account as part of the auth process
public class ApplicationStartRunner implements CommandLineRunner {
    private final RoleRepository roleRepository;
    @Override
    public void run(String ...args) throws Exception {
        //creates 2 new user with different roles
        Role roleUser = new Role(1L, "123", "ROLE_USER");
        Role roleAdmin = new Role(2L, "456", "ROLE_ADMIN");
        roleRepository.saveAll(asList(roleUser, roleAdmin));
    }
}
