package io.javatech.api.service;


import io.javatech.api.model.Employee;

import java.util.List;

public interface EmployeeService {
    // Save an employee
    Employee addEmployee(Employee employee);

    // Get all employee. In production, try to paginate the response for scalability
    List<Employee> getAllEmployees();

    // Get employee
    Employee findById(Integer id);

    //update employee
    void updateEmployee(Employee employee);

    // Delete employee
    Boolean deleteById(Integer id);
}
