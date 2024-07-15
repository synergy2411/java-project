package com.ukg.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data @AllArgsConstructor
public class ErrorResponseDto {

    private String apiPath;
    private String errorMessage;
    private HttpStatus errorStatus;
    private String errorCode;
}
