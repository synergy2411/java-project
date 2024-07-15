package com.ukg.accounts.service;

import com.ukg.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * @param customerDto - The Customer DTO
     */
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchDetails(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    void updateCommunicationStatus(Long accountNumber);
}
