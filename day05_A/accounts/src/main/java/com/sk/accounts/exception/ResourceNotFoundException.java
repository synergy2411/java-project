package com.sk.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String entity, String fieldName, String fieldValue){
        super(String.format("%s not found for %s field - %s", entity,fieldName, fieldValue));
    }
}
