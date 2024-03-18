package com.itstep.first_spring.config.exceptions;

import com.itstep.first_spring.dto.errors.ErrorResponseDTO;
import com.itstep.first_spring.exceptions.StatusException;
import com.itstep.first_spring.exceptions.auth.EmailInvalidException;
import com.itstep.first_spring.exceptions.auth.UsernameInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(ex);
        HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;

        if (ex instanceof UsernameInvalidException) {
            status = HttpStatus.BAD_REQUEST;
        } else if (ex instanceof EmailInvalidException) {
            status = HttpStatus.TOO_MANY_REQUESTS;
        }

        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(UsernameInvalidException.class)
    public ResponseEntity<ErrorResponseDTO> handleException(UsernameInvalidException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(ex);
        HttpStatus status = HttpStatus.UNSUPPORTED_MEDIA_TYPE;
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(StatusException.class)
    public ResponseEntity<ErrorResponseDTO> handleException(StatusException ex) {
        ErrorResponseDTO errorResponse = new ErrorResponseDTO(ex);
        HttpStatus status =ex.getStatus();
        return ResponseEntity.status(status).body(errorResponse);
    }
}