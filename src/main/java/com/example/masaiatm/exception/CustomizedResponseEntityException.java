package com.example.masaiatm.exception;

import com.example.masaiatm.dto.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

public class CustomizedResponseEntityException extends ResponseEntityExceptionHandler {

    // Handles all exception
    @ExceptionHandler(Exception.class)
    public final ResponseEntity handleAllException(Exception e, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handles if the user has numbers or special characters in his name
    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity handleBadRequestException(Exception e, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                e.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
