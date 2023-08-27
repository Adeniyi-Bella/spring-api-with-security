package io.javatech.api.resource;

import io.javatech.api.model.Employee;
import io.javatech.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.*;

@RestController
@RequestMapping(path="/api/employees")
//@RequiredArgsConstructor
public class EmployeeResource {
    private final EmployeeService employeeService;

    public EmployeeResource(@Qualifier(value = "mySQLEmployeeService")EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") Integer id){
        return ResponseEntity.ok(employeeService.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')") //post request must come from an admin user
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
//        employee.setId(employeeService.getAllEmployees().size() + 1);
        return ResponseEntity.created(getLocation(employee.getId())).body(employeeService.addEmployee(employee));
    }

    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee){
        employee.setId(id);
        employeeService.updateEmployee(employee);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteEmployee(@PathVariable("id") Integer id){
        return ResponseEntity.ok(employeeService.deleteById(id));
    }

    //protected so that it can be used in the same package
    protected static URI getLocation(Integer id) {
        //creates a path from the current request and pass in the id
        return fromCurrentRequest().path("{id}").buildAndExpand(id).toUri();
    }
}
