package com.ashman.sample.service;

import java.util.Map;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ashman.sample.entity.Person;

public interface PersonService {
    public Page<Person> findAllPerson(Pageable pageable);
    public Person viewPerson(UUID id);
    public Person addPerson(Person request);
    public Person updatePerson(UUID id, Person request);
    public Person patchPerson(UUID id, Map<String, Object> request);
    public void deletePerson(UUID id);
}
