package com.sk.security_demo.controller;
import com.sk.security_demo.jwt.CustomJwt;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

@RestController
@CrossOrigin(
        origins = "http://localhost:4200",
        allowedHeaders = "*",
        methods = {RequestMethod.GET}
)
public class SecurityRestController {

    @GetMapping("/hello")
//    @PreAuthorize("hasAuthority('ROLE_DEVELOPERS')")
    public Message sayHello(){
        System.out.println("------ API HIT ---------");
        CustomJwt jwt =  (CustomJwt) SecurityContextHolder.getContext().getAuthentication();
        String message = MessageFormat.format(
                "Hello fullstack Master {0} {1}, How it going today",
                jwt.getFirstName(), jwt.getLastName()
        );
        return new Message(message);
    }

    record Message(String message){};

}


