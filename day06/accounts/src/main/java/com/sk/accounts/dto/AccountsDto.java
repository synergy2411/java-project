package com.sk.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountsDto {
    private String accountNumber;
    private Long customerId;
    private String accountType;
    private String branchAddress;
}
