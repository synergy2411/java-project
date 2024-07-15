package com.ukg.accounts.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountsDto {
    private Long accountNumber;
    private String accountType;
    private String branchAddress;
}
