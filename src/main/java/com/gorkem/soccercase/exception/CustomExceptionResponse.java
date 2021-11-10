package com.gorkem.soccercase.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class CustomExceptionResponse {
    private Date timestamp;
    private String message;
    private String details;
}
