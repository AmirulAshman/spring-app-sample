package com.ashman.sample.controller;

import java.net.URI;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ashman.sample.entity.Person;
import com.ashman.sample.model.BaseResponse;
import com.ashman.sample.service.PersonService;
import com.ashman.sample.utility.ObjectMapperUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/sample/api")
public class PersonController {

    Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    PersonService personService;

    @Autowired
    HttpServletRequest servletRequest;

    @GetMapping("/person")
    public ResponseEntity<BaseResponse<Page<Person>>> findAllPerson(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        logger.info("Request: " + ObjectMapperUtil.toJson(null));
        Pageable pageable = PageRequest.of(page, size);
        Page<Person> persons = personService.findAllPerson(pageable);
        BaseResponse<Page<Person>> response = new BaseResponse<>();
        response.setMessage("Success! Person found.");
        response.setResponseCode("200");
        response.setData(persons);
        logger.info("Response: " + response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<BaseResponse<Person>> viewPerson(@PathVariable UUID id) {
        logger.info("Request: " + ObjectMapperUtil.toJson(id));
        Person person = personService.viewPerson(id);
        BaseResponse<Person> response = new BaseResponse<>();
        response.setMessage("Success! Person found.");
        response.setResponseCode("200");
        response.setData(person);
        logger.info("Response: " + response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/person")
    public ResponseEntity<BaseResponse<Person>> addPerson(
            @RequestBody(required = true) Person request) {
        logger.info("Request: " + ObjectMapperUtil.toJson(request));
        Person person = personService.addPerson(request);
        BaseResponse<Person> response = new BaseResponse<>();
        response.setMessage("Success! Person created.");
        response.setResponseCode("201");
        response.setData(person);
        URI url = URI.create(
                servletRequest.getRequestURL().append('/').append(person.getId()).toString());
        logger.info("Response: " + ObjectMapperUtil.toJson(response));
        return ResponseEntity.created(url).body(response);
    }

    @PatchMapping("/person/{id}")
    public ResponseEntity<BaseResponse<Person>> patchPerson(@PathVariable UUID id,
            @RequestBody(required = true) Map<String, Object> request) {
        BaseResponse<Person> response = new BaseResponse<>();
        logger.info("Request: " + ObjectMapperUtil.toJson(request));
        Person person = personService.patchPerson(id, request);
        response.setMessage("Success! Person patched.");
        response.setResponseCode("200");
        response.setData(person);
        logger.info("Response: " + ObjectMapperUtil.toJson(response));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<BaseResponse<Person>> updatePerson(@PathVariable UUID id,
            @RequestBody(required = true) Person request) {
        BaseResponse<Person> response = new BaseResponse<>();
        logger.info("Request: " + ObjectMapperUtil.toJson(request));
        Person person = personService.updatePerson(id, request);
        response.setMessage("Success! Person updated.");
        response.setResponseCode("200");
        response.setData(person);
        logger.info("Response: " + ObjectMapperUtil.toJson(response));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<BaseResponse<UUID>> deletePerson(@PathVariable UUID id) {
        logger.info("Request: " + ObjectMapperUtil.toJson(id));
        BaseResponse<UUID> response = new BaseResponse<>();
        personService.deletePerson(id);
        response.setMessage("Success! Person deleted.");
        response.setData(id);
        logger.info("Response: " + ObjectMapperUtil.toJson(response));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
