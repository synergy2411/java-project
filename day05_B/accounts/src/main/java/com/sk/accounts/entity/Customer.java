package com.sk.accounts.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "customer")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Customer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @NotNull(message = "Customer name can not be empty")
    @Size(min = 4, max = 30, message = "Customer name should be minimum 4 characters and maximum 30 characters long")
    private String name;

    @NotNull(message = "Email can not be empty")
    @Email(message = "Email should be in proper format")
    private String email;

    @NotNull(message = "Mobile number can not be empty")
    @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile number should have ten digits")
    private String mobileNumber;

}
