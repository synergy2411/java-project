package com.sk.accounts.service;

import com.sk.accounts.dto.CustomerDetailsDto;

public interface ICustomerDetails {

    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);

}
