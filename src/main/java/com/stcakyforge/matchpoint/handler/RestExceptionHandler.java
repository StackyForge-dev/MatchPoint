package com.stcakyforge.matchpoint.handler;

import com.stcakyforge.matchpoint.Exception.InvalidFormatException;
import com.stcakyforge.matchpoint.Exception.ConflictException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    private ResponseEntity<RestErrorMessageHandler> conflictHandler(ConflictException ex){
        RestErrorMessageHandler restErrorMessageHandler = new RestErrorMessageHandler(HttpStatus.CONFLICT, ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(restErrorMessageHandler);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<RestErrorMessageHandler> userNotFoundHandler(){
        RestErrorMessageHandler restErrorMessageHandler = new RestErrorMessageHandler(HttpStatus.NOT_FOUND, "Data not found");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restErrorMessageHandler);
    }

    @ExceptionHandler(InvalidFormatException.class)
    private ResponseEntity<RestErrorMessageHandler> invalidFormatHandler(InvalidFormatException ex){
        RestErrorMessageHandler restErrorMessageHandler = new RestErrorMessageHandler(HttpStatus.BAD_REQUEST, ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(restErrorMessageHandler);
    }
}
