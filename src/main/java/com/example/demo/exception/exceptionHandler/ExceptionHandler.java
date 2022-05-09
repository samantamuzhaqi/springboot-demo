package com.example.demo.exception.exceptionHandler;

import com.example.demo.exception.ApiError;
import com.example.demo.exception.customExceptions.EmailAlreadyExists;
import com.example.demo.exception.customExceptions.PasswordLengthException;
import com.example.demo.exception.customExceptions.UserDoesNotExist;
import com.example.demo.exception.customExceptions.UserNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFound(UserNotFoundException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND,ex.getMessage(),ex));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UserDoesNotExist.class)
    public ResponseEntity<ApiError> handleUserDoesNotExist (UserDoesNotExist ex){
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), ex));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(EmailAlreadyExists.class)
    public ResponseEntity<ApiError> handleEmailAlreadyExists(EmailAlreadyExists ex){
        return buildResponseEntity(new ApiError(HttpStatus.CONFLICT, ex.getMessage(), ex));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(PasswordLengthException.class)
    public ResponseEntity<ApiError> handlePasswordLengthException (PasswordLengthException ex){
        return buildResponseEntity(new ApiError(HttpStatus.CONFLICT, ex.getMessage(), ex));
    }

    public ResponseEntity<ApiError> buildResponseEntity (ApiError apiError){
        return new ResponseEntity(apiError, apiError.getStatus());
    }
}
