package com.jobisnvillains.UserRefundCalculator.exception;

import com.jobisnvillains.UserRefundCalculator.exception.customexceptions.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RegNoIllegalStateException.class)
    public ResponseEntity RegNoExHandler(RegNoIllegalStateException e) {
        ErrorResult errorResult = new ErrorResult("RegNoIllegalStateException", e.getMessage());
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotDefineUserStateException.class)
    public ResponseEntity NotDefineUserExHandler(NotDefineUserStateException e) {
        ErrorResult errorResult = new ErrorResult("NotDefineUserStateException", e.getMessage());
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyUserStateException.class)
    public ResponseEntity AlreadyUserExHandler(AlreadyUserStateException e) {
        ErrorResult errorResult = new ErrorResult("AlreadyUserStateException", e.getMessage());
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotRegisterUserStateException.class)
    public ResponseEntity NotRegisterUserExHandler(NotRegisterUserStateException e) {
        ErrorResult errorResult = new ErrorResult("NotRegisterUserStateException", e.getMessage());
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyAPIDataStateException.class)
    public ResponseEntity EmptyAPIDataExHandler(EmptyAPIDataStateException e) {
        ErrorResult errorResult = new ErrorResult("EmptyAPIDataStateException", e.getMessage());
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyIncomeDataStateException.class)
    public ResponseEntity EmptyIncomeDataExHandler(EmptyIncomeDataStateException e) {
        ErrorResult errorResult = new ErrorResult("EmptyIncomeDataStateException", e.getMessage());
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
    }

    @Data
    @AllArgsConstructor
    public class ErrorResult {
        private String code;
        private String message;
    }
}
