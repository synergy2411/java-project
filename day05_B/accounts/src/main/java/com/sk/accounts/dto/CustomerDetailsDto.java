package com.sk.accounts.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CustomerDetailsDto {
    private String name;
    private String email;
    private String mobileNumber;
    private AccountsDto accountsDto;
    private CardsDto cardsDto;
    private LoansDto loansDto;
}
