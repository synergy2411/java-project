package com.sk.security_demo.controller;
import com.sk.security_demo.dto.ResponseDto;
import com.sk.security_demo.jwt.CustomJwt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

@RestController
public class SecurityRestController {

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('ROLE_EMP')")
    public Message sayHello(){
        CustomJwt jwt =  (CustomJwt) SecurityContextHolder.getContext().getAuthentication();
        String message = MessageFormat.format(
                "Hello fullstack Master {0} {1} {2}, How it going today",
                jwt.getFirstName(), jwt.getLastName(), jwt.getRoles()
        );
        return new Message(message);
    }

    @GetMapping("/get-my-details")
    @PreAuthorize("hasRole('ROLE_EMP')")
    public ResponseEntity<ResponseDto> getUserDetails(){
        CustomJwt jwt =  (CustomJwt) SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto(
                        jwt.getFirstName(),
                        jwt.getLastName(),
                        jwt.getEmail(),
                        jwt.getRoles()
                ));
    }

    record Message(String message){};

}


