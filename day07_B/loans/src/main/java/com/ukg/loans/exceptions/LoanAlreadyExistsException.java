package com.ukg.loans.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LoanAlreadyExistsException extends RuntimeException{

    public LoanAlreadyExistsException(String message){
        super(message);
    }
}
