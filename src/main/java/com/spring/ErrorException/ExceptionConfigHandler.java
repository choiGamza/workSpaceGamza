package com.spring.ErrorException;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.ServletException;
import java.io.FileNotFoundException;
import java.io.IOException;

@ControllerAdvice
public interface ExceptionConfigHandler {

    @Slf4j
    final class LogHolder{}

    @ExceptionHandler({Exception.class, RuntimeException.class, IOException.class})
    public static ResponseEntity<?> ExceptionEntity(Exception e){
        LogHolder.log.info("Exception Error Message ::: " + e.getMessage());
        ErrorResponse response = new ErrorResponse(ErrorCode.BAD_REQUEST);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NullPointerException.class, FileNotFoundException.class})
    public static ResponseEntity<?> NullPointerExceptionEntity(NullPointerException e){
        LogHolder.log.info("NullPointerException Error Message ::: " + e.getMessage());
        ErrorResponse response = new ErrorResponse(ErrorCode.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpException.class)
    public static ResponseEntity<?> HttpExceptionEntity(HttpException e){
        LogHolder.log.info("HttpExceptionEntity Error Message ::: " + e.getMessage());
        ErrorResponse response = new ErrorResponse(ErrorCode.ALREADY_CONFLICT);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ServletException.class)
    public static ResponseEntity<?> ParserConfigurationExceptionEntity(ServletException e){
        LogHolder.log.info("ParserConfigurationException Error Message ::: " + e.getMessage());
        ErrorResponse response = new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
