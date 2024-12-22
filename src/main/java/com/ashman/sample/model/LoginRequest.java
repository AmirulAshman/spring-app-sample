package com.ashman.sample.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.Generated;

@Data
@Generated
@JsonInclude(Include.NON_NULL)
public class LoginRequest {
    private String username;
    private String password;
}
