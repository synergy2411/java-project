package com.ukg.accounts.mapper;

import com.ukg.accounts.dto.AccountsDto;
import com.ukg.accounts.dto.CustomerDto;
import com.ukg.accounts.entity.Accounts;
import com.ukg.accounts.entity.Customer;

public class CustomerMapper {

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer){
       customer.setEmail(customerDto.getEmail());
       customer.setName(customerDto.getName());
       customer.setMobileNumber(customerDto.getMobileNumber());
       return customer;
    }

    public static CustomerDto mapToCustomerDto(Customer customer,CustomerDto customerDto){
        customerDto.setEmail(customer.getEmail());
        customerDto.setName(customer.getName());
        customerDto.setMobileNumber(customer.getMobileNumber());
        return customerDto;
    }
}
