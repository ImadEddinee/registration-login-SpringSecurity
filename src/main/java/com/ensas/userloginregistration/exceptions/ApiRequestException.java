package com.ensas.userloginregistration.exceptions;

public class ApiRequestException extends RuntimeException {

    public ApiRequestException(String message){
        super(message);
    }
}
