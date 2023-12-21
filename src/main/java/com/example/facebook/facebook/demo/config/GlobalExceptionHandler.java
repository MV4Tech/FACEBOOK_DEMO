package com.example.facebook.facebook.demo.config;

import com.example.facebook.facebook.demo.exception.*;
import com.example.facebook.facebook.demo.model.Address;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // This method is used to handle the validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex){
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }


    // This method is used to handle the UserNotFoundException
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, List<String>>> handleNotFoundException(UserNotFoundException ex){
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    // This method is used to handle the AddressNotFoundException
    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<Map<String,List<String>>> handleAddressNotFoundException(AddressNotFoundException ex){
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
    // This method is used to handle the CompanyNotFoundException
    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<Map<String,List<String>>> handleCompanyNotFoundException(CompanyNotFoundException ex){
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    // This method is used to handle the EducationNotFoundException
    @ExceptionHandler(EducationNotFoundException.class)
    public ResponseEntity<Map<String,List<String>>> handleEducationNotFoundException(EducationNotFoundException ex){
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String,List<String>>> handleInvalidCredentialsException(InvalidCredentialsException ex){
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
/*
    // This method is used to handle the general exceptions
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Map<String, List<String>>> handleGenerealException(Exception ex){
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    */

/*
    // This method is used to handle the runtime exceptions
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String,List<String>>> handleRunetimeException(RuntimeException ex){
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
*/
    // This method is used to create the error response in the form of a map
    private Map<String,List<String>> getErrorsMap(List<String> errors) {
        Map<String,List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors",errors);
        return errorResponse;
    }


}
