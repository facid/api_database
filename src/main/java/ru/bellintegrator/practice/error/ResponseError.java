package ru.bellintegrator.practice.error;

/**
 * Класс для оборачивания тела ответа в {error}
 */

public class ResponseError {
    private String error;

    public ResponseError(String error){
        this.error = error;
    }

    public String getError(){
        return error;
    }

    public void setError(String error){
        this.error = error;
    }
}
