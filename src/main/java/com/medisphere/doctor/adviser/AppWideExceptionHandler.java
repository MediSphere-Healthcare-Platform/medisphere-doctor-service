package com.medisphere.doctor.adviser;

import com.medisphere.doctor.exception.EntryNotFoundException;
import com.medisphere.doctor.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(EntryNotFoundException.class)
    public ResponseEntity<StandardResponse> handleEntryNotFoundException(EntryNotFoundException e) {
        return new ResponseEntity<>(
                new StandardResponse(404, e.getMessage(), e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardResponse> handleGeneralException(Exception e) {
        return new ResponseEntity<>(
                new StandardResponse(500, e.getMessage(), "Internal Server Error"),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
