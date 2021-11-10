package com.gorkem.soccercase.exception;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class CustomExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;
}
