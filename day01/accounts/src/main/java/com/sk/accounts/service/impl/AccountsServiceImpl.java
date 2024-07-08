package com.sk.accounts.service.impl;

import com.sk.accounts.dto.CustomerDto;
import com.sk.accounts.entity.Accounts;
import com.sk.accounts.entity.Customer;
import com.sk.accounts.exception.CustomerAlreadyExistsException;
import com.sk.accounts.mapper.CustomerMapper;
import com.sk.accounts.repository.AccountsRepository;
import com.sk.accounts.repository.CustomerRepository;
import com.sk.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private final CustomerRepository customerRepository;
    private final AccountsRepository accountsRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {

        Optional<Customer> foundCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());

        if(foundCustomer.isPresent()){
            throw new CustomerAlreadyExistsException("Customer already exists for this mobile number - " + customerDto.getMobileNumber());
        }

        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Anonymous");
        customerRepository.save(customer);

        Accounts accounts = createNewAccount(customer.getCustomerId());
        accountsRepository.save(accounts);
    }

    private Accounts createNewAccount(Long customerId) {
        Accounts accounts = new Accounts();
        accounts.setCustomerId(customerId);
        accounts.setAccountType("Savings");
        long randomAccNumber = 10000000000L + new Random().nextInt(900000000);
        accounts.setAccountNumber(String.valueOf(randomAccNumber));
        accounts.setBranchAddress("201, Main Road, London");
        accounts.setCreatedAt(LocalDateTime.now());
        accounts.setCreatedBy("Anonymous");
        return accounts;
    }
}
