package com.ukg.loans.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
public class ErrorResponseDto {

    private String apiPath;
    private HttpStatus statusCode;
    private LocalDateTime errorTime;
    private String errorMessage;

}
