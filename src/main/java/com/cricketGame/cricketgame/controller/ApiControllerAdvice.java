package com.cricketGame.cricketgame.controller;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(10)
public class ApiControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handle(RuntimeException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(401));
    }
}
