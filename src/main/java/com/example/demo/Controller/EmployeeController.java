package com.example.demo.Controller;

import com.example.demo.Controller.Dto.EmployeeResponse;
import com.example.demo.Service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/list")
    public List<EmployeeResponse> getAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable long id) {
        return employeeService.findById(id);
    }

    @PostMapping
    public EmployeeResponse addEmployee(@RequestBody EmployeeResponse employeeResponse) {
        return employeeService.save(employeeResponse);
    }

    @PutMapping("/{id}")
    public EmployeeResponse updateEmployee(@PathVariable long id, @RequestBody EmployeeResponse employeeResponse) {
        EmployeeResponse existingEmployee = employeeService.findById(id);

        return employeeService.save(
                EmployeeResponse.builder()
                        .id(existingEmployee.getId())
                        .firstName(employeeResponse.getFirstName())
                        .lastName(employeeResponse.getLastName())
                        .email(employeeResponse.getEmail())
                        .age(employeeResponse.getAge())
                        .build());
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable long id) {
        employeeService.deleteById(id);
    }
}
