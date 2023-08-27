package io.javatech.apisecurity.repository;

import io.javatech.apisecurity.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaEmployeeRepository extends JpaRepository<Employee, Integer> {
    //Define more methods that are not in the jpa repo
}
