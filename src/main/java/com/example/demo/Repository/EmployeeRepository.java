package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public List<Employee> findAllByOrderByLastNameAsc();

    public Optional<Employee> findById(long theId);

    public void deleteById(long theId);
}
