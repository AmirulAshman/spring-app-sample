package com.ashman.sample.service.implementation;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.ashman.sample.entity.Person;
import com.ashman.sample.exception.InvalidFieldException;
import com.ashman.sample.repository.PersonRepository;
import com.ashman.sample.service.PersonService;
import com.ashman.sample.utility.EntityUpdateUtil;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public Page<Person> findAllPerson(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    @Override
    public Person viewPerson(UUID id) {
        return personRepository.findById(id).get();
    }

    @Override
    public Person addPerson(Person request) {
        return personRepository.save(request);
    }

    @Override
    public Person updatePerson(UUID id, Person request) {
        Person person = personRepository.findById(id).get();
        person = request;
        person.setId(id);
        return personRepository.save(person);
    }

    public Person patchPerson(UUID id, Map<String, Object> request) {
        Person person = personRepository.findById(id).get();
        for (String field : request.keySet()) {
            try {
                EntityUpdateUtil.updateFieldValue(person, request.get(field), field);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                if (e instanceof NoSuchMethodException) {
                    throw new InvalidFieldException(field);
                }
                e.printStackTrace();
            }
        }
        return updatePerson(id, person);
    }

    @Override
    public void deletePerson(UUID id) {
        personRepository.deleteById(id);
    }

}
