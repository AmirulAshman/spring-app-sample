package com.ashman.sample.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ashman.sample.configuration.security.JwtUtil;
import com.ashman.sample.model.BaseResponse;
import com.ashman.sample.model.LoginRequest;
import com.ashman.sample.utility.ObjectMapperUtil;

@RestController
public class LoginController {

    Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<String>> login(@RequestBody(required = true) LoginRequest request) {
        logger.info("Request: " + ObjectMapperUtil.toJson(request));
        if (StringUtils.equals(request.getPassword(), "password")) {
            String token = JwtUtil.generateToken(request.getUsername());
            BaseResponse<String> response = new BaseResponse<>();
            response.setMessage("Login success!");
            response.setResponseCode("200");
            response.setData(token);
            logger.info("Response: " + ObjectMapperUtil.toJson(response));
            return ResponseEntity.ok().body(response);
        } else {
            BaseResponse<String> response = new BaseResponse<>();
            response.setMessage("Invalid password!");
            response.setResponseCode("401");
            response.setData(null);
            logger.info("Response: " + ObjectMapperUtil.toJson(response));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
