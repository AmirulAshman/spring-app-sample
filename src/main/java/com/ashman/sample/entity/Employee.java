package com.ashman.sample.entity;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Generated;

@Entity
@Table(name = "EMPLOYEE")
@Data
@Generated
public class Employee {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private UUID id;

    @Column(name = "FULL_NAME", nullable = false, length = 255)
    private String fullName;

    @Column(name = "BIRTH_DATE", length = 20)
    private String birthDate;

    @Column(name = "DEPARTMENT", length = 20)
    private String department;

    @Column(name = "SALARY", precision = 10, scale = 2)
    private BigDecimal salary;
}
