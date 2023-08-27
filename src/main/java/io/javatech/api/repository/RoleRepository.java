package io.javatech.api.repository;

import io.javatech.api.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    //returns a role with a certain name
    Role findByName(String name);
}