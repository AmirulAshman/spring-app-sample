package com.ashman.sample.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashman.sample.entity.Person;

public interface PersonRepository extends JpaRepository<Person, UUID> {

}