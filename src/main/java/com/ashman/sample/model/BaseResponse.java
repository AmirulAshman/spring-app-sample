package com.ashman.sample.model;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.Generated;

@Data
@Generated
@JsonInclude(Include.NON_NULL)
public class BaseResponse<T extends Object> {
    private String method;
    private String message;
    private String responseCode;
    private String errorMessage;
    private String errorCode;
    private Instant errorTime;
    private T data;
}
