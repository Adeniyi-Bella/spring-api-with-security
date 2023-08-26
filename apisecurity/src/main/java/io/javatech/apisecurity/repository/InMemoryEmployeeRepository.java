package io.javatech.apisecurity.repository;

import java.util.ArrayList;
import java.util.List;

import io.javatech.apisecurity.model.Employee;
import org.springframework.stereotype.Repository;

import static java.lang.Boolean.TRUE;

@Repository
public class InMemoryEmployeeRepository {
    //declares a constant List named DATABASE that holds objects of type Employee
    private static final List<Employee> DATABASE = new ArrayList<>();

    // To be loaded in the array on DB on start up
    static {
        DATABASE.add(new Employee(1, "John", "Smith", "John@gmail.com"));
        DATABASE.add(new Employee(2, "Alex", "Bella", "alex@gmail.com"));
        DATABASE.add(new Employee(3, "David", "Smith", "Davidn@gmail.com"));
    }
    public Employee addEmployee(Employee employee){

        DATABASE.add(employee);
        return employee;
    }

    public List<Employee> getAllEmployees(){
        return List.copyOf(DATABASE);
    }

    // Get employee
    public Employee findById(Integer id){
        return DATABASE
                .stream() // look for
                .filter(emp -> id.equals(emp.getId())) //
                .findFirst()
                .orElseThrow(); //throw an exception if no match
    }

    public void updateEmployee(Employee employee){
        Employee existingEmployee = DATABASE
                .stream()
                .filter(emp -> employee.getId().equals(emp.getId()))
                .findFirst()
                .orElseThrow();

        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());
    }


    // Delete employee
    public Boolean deleteById(Integer id){
        //get the employee to be deleted
        Employee employee = DATABASE
                .stream()
                .filter(emp -> id.equals(emp.getId()))
                .findFirst()
                .orElseThrow();
        //if you find employee, then remove
        DATABASE.remove(employee);
        return TRUE;
    }
}
