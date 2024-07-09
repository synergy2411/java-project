package com.sk.accounts.service;

import com.sk.accounts.dto.CustomerDto;

public interface IAccountsService {

    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccountDetails(String mobileNumber);

    boolean updateAccount(String mobileNumber, CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}
