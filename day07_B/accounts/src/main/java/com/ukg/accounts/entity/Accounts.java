package com.ukg.accounts.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "accounts")
public class Accounts extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_number")
    @NotNull (message = "Account number can not be empty")
    private Long accountNumber;

    @NotNull(message = "Customer ID can not be empty")
    @Column(name = "customer_id")
    private Long customerId;

    @NotNull(message = "Account type can not be empty")
    @Column(name = "account_type")
    private String accountType;

    @NotNull
    @Size(min = 6, max = 300, message = "Branch address should be minimum 6 characters and maximum 300 characters long")
    @Column(name = "branch_address")
    private String branchAddress;


    @Column(name = "communication_status")
    private boolean communicationStatus;

}
