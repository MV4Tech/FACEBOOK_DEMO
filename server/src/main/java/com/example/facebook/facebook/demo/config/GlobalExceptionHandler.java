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
    public ResponseEntity<Map<String, List<String>>> handleUserNotFoundException(UserNotFoundException ex){
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


    // This method is used to handle the InvalidCredentialsException
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String,List<String>>> handleInvalidCredentialsException(InvalidCredentialsException ex){
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    // This method is used to handle the Unique Constraint Exception
    @ExceptionHandler(UniqueConstraintException.class)
    public ResponseEntity<Map<String,List<String>>> handleUniqueConstraintException(UniqueConstraintException ex){
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    // This method is used to handle PostNotFoundException
    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<Map<String,List<String>>> handlePostNotFoundException(PostNotFoundException ex){
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    // This method is used to handle ReactionNotFoundException
    @ExceptionHandler(ReactionNotFoundException.class)
    public ResponseEntity<Map<String,List<String>>> handleReactionNotFoundException(ReactionNotFoundException ex){
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    // This method is used to handle CommentNotFoundException
    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<Map<String,List<String>>> handleCommentNotFoundException(CommentNotFoundException ex){
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    // This method is used to handle PostPhotoNotFoundException
    @ExceptionHandler(PostPhotoNotFoundException.class)
    public ResponseEntity<Map<String,List<String>>> handlePostPhotoNotFoundException(PostPhotoNotFoundException ex){
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    // This method is used to handle PostVideoNotFoundException
    @ExceptionHandler(PostVideoNotFoundException.class)
    public ResponseEntity<Map<String,List<String>>> handlePostVideoNotFoundException(PostVideoNotFoundException ex){
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    // This method is used to handle NotificationsNotFoundException
    @ExceptionHandler(NotificationsNotFoundException.class)
    public ResponseEntity<Map<String,List<String>>> handleNotificationNotFoundException(NotificationsNotFoundException ex){
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    // This method is used to handle FriendshipNotFoundException
    @ExceptionHandler(FriendshipNotFoundException.class)
    public ResponseEntity<Map<String,List<String>>> handleFriendshipNotFoundException(FriendshipNotFoundException ex){
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }


    // This method is used to handle PageNotFoundException
    @ExceptionHandler(PageNotFoundException.class)
    public ResponseEntity<Map<String,List<String>>> handlePageNotFoundException(PageNotFoundException ex){
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(UserPageRelationNotFoundException.class)
    public ResponseEntity<Map<String,List<String>>> handleUserPageRelationNotFoundException(UserPageRelationNotFoundException ex){
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.NOT_FOUND);
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
