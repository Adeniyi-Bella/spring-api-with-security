package io.javatech.apisecurity.repository;

import java.util.ArrayList;
import java.util.List;

import io.javatech.apisecurity.model.Employee;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryEmployeeRepository {
    //declares a constant List named DATABASE that holds objects of type Employee
    private static final List<Employee> DATABASE = new ArrayList<>();

    // To be loaded in the DB on start up
    static {
        DATABASE.add(new Employee(1, "John", "Smith", "John@gmail.com"));
        DATABASE.add(new Employee(2, "Alex", "Bella", "alex@gmail.com"));
        DATABASE.add(new Employee(3, "David", "Smith", "Davidn@gmail.com"));

    }
}
