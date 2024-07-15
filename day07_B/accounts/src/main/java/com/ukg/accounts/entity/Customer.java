package com.ukg.accounts.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @NotNull(message = "Customer name can not be empty")
    @Column(name = "name")
    private String name;

    @Column(name = "email")
    @NotNull(message = "Customer email can not be empty")
    private String email;

    @Column(name = "mobile_number")
    @NotNull(message = "Mobile number can not be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should have 10 digits")
    private String mobileNumber;
}
