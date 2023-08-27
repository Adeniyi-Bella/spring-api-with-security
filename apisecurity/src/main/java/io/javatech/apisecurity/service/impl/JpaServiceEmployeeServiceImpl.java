package io.javatech.apisecurity.service.impl;

import io.javatech.apisecurity.model.Employee;
import io.javatech.apisecurity.repository.JpaEmployeeRepository;
import io.javatech.apisecurity.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
@Qualifier(value = "mySQLEmployeeService")
public class JpaServiceEmployeeServiceImpl implements EmployeeService {
    private final JpaEmployeeRepository jpaEmployeeRepository;
    @Override
    public Employee addEmployee(Employee employee) {
        validateEmployee(employee);
        return jpaEmployeeRepository.save(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return jpaEmployeeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Employee findById(Integer id) {
        return jpaEmployeeRepository.findById(id).get();
    }

    @Override
    public void updateEmployee(Employee employee) {
        // Check if employee with the given id exists
        validateEmployee(employee);

        Integer id = employee.getId();
        if (id == null || !jpaEmployeeRepository.existsById(id)) {
            throw new IllegalArgumentException("Employee with id " + id + " does not exist.");
        }
        jpaEmployeeRepository.save(employee);
    }

    @Override
    public Boolean deleteById(Integer id) {
        if (jpaEmployeeRepository.existsById(id)) {
            jpaEmployeeRepository.deleteById(id);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
    private void validateEmployee(Employee employee) {
        // Check if employee is not null
        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }

        // Check if employee name is not null or empty
        String name = employee.getFirstName();
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee name cannot be null or empty");
        }

        // Check if employee email is not null or empty
        String email = employee.getEmail();
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee email cannot be null or empty");
        }

        // Check if employee email has a valid format
        String emailRegex = "^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("Employee email is not valid");
        }

        // Add more validations as needed
    }


}
