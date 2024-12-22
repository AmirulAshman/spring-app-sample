package com.ashman.sample.service.implementation;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ashman.sample.entity.Employee;
import com.ashman.sample.exception.InvalidFieldException;
import com.ashman.sample.repository.EmployeeRepository;
import com.ashman.sample.service.EmployeeService;
import com.ashman.sample.utility.EntityUpdateUtil;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public Page<Employee> findAllEmployee(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee viewEmployee(UUID id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public Employee addEmployee(Employee request) {
        return employeeRepository.save(request);
    }

    @Override
    public Employee updateEmployee(UUID id, Employee request) {
        Employee employee = employeeRepository.findById(id).get();
        employee = request;
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    @Override
    public Employee patchEmployee(UUID id, Map<String, Object> request) {
        Employee employee = employeeRepository.findById(id).get();
        for (String field : request.keySet()) {
            try {
                EntityUpdateUtil.updateFieldValue(employee, request.get(field), field);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                if (e instanceof NoSuchMethodException) {
                    throw new InvalidFieldException(field);
                }
                e.printStackTrace();
            }
        }
        return updateEmployee(id, employee);
    }

    @Override
    public void deleteEmployee(UUID id) {
        employeeRepository.deleteById(id);
    }

}
