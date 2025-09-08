package com.softdesign.vote.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleOtherExceptions(Exception ex) {
        log.error("Erro inesperado: ", ex);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body("Erro inesperado: " + ex.getMessage());
    }
}
