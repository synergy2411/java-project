package com.sk.accounts.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "accounts")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Accounts extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @NotNull(message = "Account number can not be empty")
    private String accountNumber;

    @NotNull(message = "Customer Id can not be empty")
    private Long customerId;

    @NotNull(message = "Account type can not be empty")
    private String accountType;

    @NotNull(message = "Branch Address can not be empty")
    private String branchAddress;

}
