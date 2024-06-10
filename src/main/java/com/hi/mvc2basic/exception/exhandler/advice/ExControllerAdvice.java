package com.hi.mvc2basic.exception.exhandler.advice;

import com.hi.mvc2basic.exception.UserException;
import com.hi.mvc2basic.exception.exhandler.ApiExceptionV2Controller;
import com.hi.mvc2basic.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
//@RestControllerAdvice(annotations = RestController.class)
//@RestControllerAdvice("com.hi.mvc2basic.exception.exhandler")
@RestControllerAdvice(assignableTypes = {ApiExceptionV2Controller.class})
public class ExControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandler(IllegalArgumentException e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandler(UserException e) {
        log.error("exceptionHandler e", e.getMessage());
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) {
        log.error("[exceptionHandler] e", e.getMessage());
        return new ErrorResult("EX", "내부 오류");
    }
}
