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
import com.ashman.sample.entity.Employee;
import com.ashman.sample.model.BaseResponse;
import com.ashman.sample.service.EmployeeService;
import com.ashman.sample.utility.ObjectMapperUtil;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/sample/api")
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;

    @Autowired
    HttpServletRequest servletRequest;

    @GetMapping("/employee")
    public ResponseEntity<BaseResponse<Page<Employee>>> findAllPerson(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        logger.info("Request: " + ObjectMapperUtil.toJson(null));
        Pageable pageable = PageRequest.of(page, size);
        Page<Employee> persons = employeeService.findAllEmployee(pageable);
        BaseResponse<Page<Employee>> response = new BaseResponse<>();
        response.setMessage("Success! Employee found.");
        response.setResponseCode("200");
        response.setData(persons);
        logger.info("Response: " + response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<BaseResponse<Employee>> viewPerson(@PathVariable UUID id) {
        logger.info("Request: " + ObjectMapperUtil.toJson(id));
        Employee employee = employeeService.viewEmployee(id);
        BaseResponse<Employee> response = new BaseResponse<>();
        response.setMessage("Success! Employee found.");
        response.setResponseCode("200");
        response.setData(employee);
        logger.info("Response: " + response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/employee")
    public ResponseEntity<BaseResponse<Employee>> addPerson(@RequestBody(required = true) Employee request) {
        logger.info("Request: " + ObjectMapperUtil.toJson(request));
        Employee employee = employeeService.addEmployee(request);
        BaseResponse<Employee> response = new BaseResponse<>();
        response.setMessage("Success! Employee created.");
        response.setResponseCode("201");
        response.setData(employee);
        URI url = URI.create(servletRequest.getRequestURL().append('/').append(employee.getId()).toString());
        logger.info("Response: " + ObjectMapperUtil.toJson(response));
        return ResponseEntity.created(url).body(response);
    }

    @PatchMapping("/employee/{id}")
    public ResponseEntity<BaseResponse<Employee>> patchPerson(@PathVariable UUID id,
            @RequestBody(required = true) Map<String, Object> request) {
        BaseResponse<Employee> response = new BaseResponse<>();
        logger.info("Request: " + ObjectMapperUtil.toJson(request));
        Employee employee = employeeService.patchEmployee(id, request);
        response.setMessage("Success! Employee patched.");
        response.setResponseCode("200");
        response.setData(employee);
        logger.info("Response: " + ObjectMapperUtil.toJson(response));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/employee/{id}")
    public ResponseEntity<BaseResponse<Employee>> updatePerson(@PathVariable UUID id,
            @RequestBody(required = true) Employee request) {
        BaseResponse<Employee> response = new BaseResponse<>();
        logger.info("Request: " + ObjectMapperUtil.toJson(request));
        Employee employee = employeeService.updateEmployee(id, request);
        response.setMessage("Success! Employee updated.");
        response.setResponseCode("200");
        response.setData(employee);
        logger.info("Response: " + ObjectMapperUtil.toJson(response));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<BaseResponse<UUID>> deletePerson(@PathVariable UUID id) {
        logger.info("Request: " + ObjectMapperUtil.toJson(id));
        BaseResponse<UUID> response = new BaseResponse<>();
        employeeService.deleteEmployee(id);
        response.setMessage("Success! Employee deleted.");
        response.setData(id);
        logger.info("Response: " + ObjectMapperUtil.toJson(response));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
