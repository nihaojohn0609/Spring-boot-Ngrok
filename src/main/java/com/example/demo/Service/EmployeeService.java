package com.example.demo.Service;

import com.example.demo.Controller.Dto.EmployeeResponse;
import com.example.demo.Repository.Employee;
import com.example.demo.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<EmployeeResponse> findAll() {
        List<EmployeeResponse> employees = new ArrayList<>();

        List<Employee> result = employeeRepository.findAll();

        for(Employee employee: result) {
            EmployeeResponse response = EmployeeResponse.builder()
                    .id(employee.getId())
                    .firstName(employee.getFirstName())
                    .lastName(employee.getLastName())
                    .email(employee.getEmail())
                    .age(employee.getAge())
                    .build();
            employees.add(response);
        }
        return employees;
    }

    public EmployeeResponse findById(long theId) {
        Optional<Employee> result = employeeRepository.findById(theId);

        if (result.isPresent()) {
            Employee employee = result.get();
            return EmployeeResponse.builder()
                    .id(employee.getId())
                    .firstName(employee.getFirstName())
                    .lastName(employee.getLastName())
                    .email(employee.getEmail())
                    .age(employee.getAge())
                    .build();
        } else {
            throw new RuntimeException("Employee not found with id - " + theId);
        }
    }

    public EmployeeResponse save(EmployeeResponse employeeResponse) {
        Employee employee = Employee.builder()
                .firstName(employeeResponse.getFirstName())
                .lastName(employeeResponse.getLastName())
                .email(employeeResponse.getEmail())
                .age(employeeResponse.getAge())
                .build();

        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeResponse.builder()
                .id(savedEmployee.getId())
                .firstName(savedEmployee.getFirstName())
                .lastName(savedEmployee.getLastName())
                .email(savedEmployee.getEmail())
                .age(savedEmployee.getAge())
                .build();
    }

    public void deleteById(long theId) {
        if(!employeeRepository.existsById(theId)) {
            throw new RuntimeException("Employee not found with id - " + theId);
        }
        employeeRepository.deleteById(theId);
    }

}
