package ru.bellintegrator.practice.error;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResponseWrapperError {

    /**
     * Перехватывает исключения
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseError handleException(Exception e){
        ResponseError error = new ResponseError(e.getMessage());
        return error;
    }
}

