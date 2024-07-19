package com.sk.security_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class ResponseDto {
    private String firstName;
    private String lastName;
    private String email;
    private List<GrantedAuthority> roles;

}
