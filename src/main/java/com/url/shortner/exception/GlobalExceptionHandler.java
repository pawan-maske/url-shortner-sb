package com.url.shortner.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String> > methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
        Map<String, String> collect = ex.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(
                        FieldError::getField,
                        fieldError -> fieldError.getDefaultMessage() != null ? fieldError.getDefaultMessage() : "Error in this field"
                )
        );

        List<String> list = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> fieldError.getField() + " : " + fieldError.getDefaultMessage()).toList();
        return ResponseEntity.badRequest().body(collect);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<Object, String>> constraintViolationException(ConstraintViolationException ex){
        Map<Object, String> collect2 = ex.getConstraintViolations().stream()
                .collect(Collectors.toMap(
                        ConstraintViolation::getInvalidValue,
                        ConstraintViolation::getMessage
                ));
        return ResponseEntity.badRequest().body(collect2);
    }
}
