package com.bridgelabz.quantitymeasurement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = {RestController.class})
public class QuantityExceptionHandler {
    @ExceptionHandler(value = QuantityMeasurementException.class)
    public ResponseEntity<Object> exceptionHandler(QuantityMeasurementException quantityException){
        return new ResponseEntity<>(quantityException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<Object> exceptionHandler1(NullPointerException nullExcepion){
        return new ResponseEntity<>("Null Pointer Exception", HttpStatus.BAD_REQUEST);
    }

}
