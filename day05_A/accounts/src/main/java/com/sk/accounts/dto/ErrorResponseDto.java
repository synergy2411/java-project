package com.sk.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
public class ErrorResponseDto {

    private String errorMessage;
    private String apiPath;
    private LocalDateTime errorTime;
    private HttpStatus statusCode;
}
