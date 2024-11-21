package com.ashman.sample.service;

import java.util.Map;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ashman.sample.entity.Employee;

public interface EmployeeService {
    public Page<Employee> findAllEmployee(Pageable pageable);

    public Employee viewEmployee(UUID id);

    public Employee addEmployee(Employee request);

    public Employee updateEmployee(UUID id, Employee request);

    public Employee patchEmployee(UUID id, Map<String, Object> request);

    public void deleteEmployee(UUID id);
}
