package com.sk.accounts.service.impl;

import com.sk.accounts.dto.AccountsDto;
import com.sk.accounts.dto.CardsDto;
import com.sk.accounts.dto.CustomerDetailsDto;
import com.sk.accounts.dto.LoansDto;
import com.sk.accounts.entity.Accounts;
import com.sk.accounts.entity.Customer;
import com.sk.accounts.exception.ResourceNotFoundException;
import com.sk.accounts.mapper.AccountsMapper;
import com.sk.accounts.mapper.CustomerMapper;
import com.sk.accounts.repository.AccountsRepository;
import com.sk.accounts.repository.CustomerRepository;
import com.sk.accounts.service.ICustomerDetails;
import com.sk.accounts.service.clients.CardsFeignClient;
import com.sk.accounts.service.clients.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerDetailsImpl implements ICustomerDetails {

    private final CustomerRepository customerRepository;
    private final AccountsRepository accountsRepository;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());

        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
}
