package com.ashman.sample.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ashman.sample.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

}
