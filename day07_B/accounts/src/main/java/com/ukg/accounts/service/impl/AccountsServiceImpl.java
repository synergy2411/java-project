package com.ukg.accounts.service.impl;

import com.ukg.accounts.dto.AccountsDto;
import com.ukg.accounts.dto.AccountsMsgDto;
import com.ukg.accounts.dto.CustomerDto;
import com.ukg.accounts.entity.Accounts;
import com.ukg.accounts.entity.Customer;
import com.ukg.accounts.exceptions.ResourceAlreadyExistsException;
import com.ukg.accounts.exceptions.ResourceNotFoundException;
import com.ukg.accounts.mapper.AccountsMapper;
import com.ukg.accounts.mapper.CustomerMapper;
import com.ukg.accounts.repository.AccountsRepository;
import com.ukg.accounts.repository.CustomerRepository;
import com.ukg.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor

public class AccountsServiceImpl implements IAccountsService {

    private static final Logger logger = LoggerFactory.getLogger(AccountsServiceImpl.class);
    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final StreamBridge streamBridge;

    /**
     * @param customerDto - The Customer DTO
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Optional<Customer> foundCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(foundCustomer.isPresent()){
            throw new ResourceAlreadyExistsException("Customer already exists with mobile number "+ customerDto.getMobileNumber());
        }
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
//        customer.setCreatedAt(LocalDateTime.now());
//        customer.setCreatedBy("Anonymous");
        Customer savedCustomer = customerRepository.save(customer);
        Accounts accountForCustomer = createAccountForCustomer(savedCustomer);
        Accounts savedAccounts = accountsRepository.save(accountForCustomer);
        createMessage(savedAccounts, savedCustomer);

    }

    private void createMessage(Accounts savedAccounts, Customer savedCustomer) {
        AccountsMsgDto accountsMsgDto = new AccountsMsgDto(savedAccounts.getAccountNumber(), savedCustomer.getName(), savedCustomer.getEmail(), savedCustomer.getMobileNumber());
        boolean isSend = streamBridge.send("sendCommunication-out-0", accountsMsgDto);
        logger.info("Is Communication send? - {}", isSend);
    }


    private Accounts createAccountForCustomer(Customer customer) {
        Accounts accounts = new Accounts();
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        accounts.setAccountNumber(randomAccNumber);
        accounts.setCustomerId(customer.getCustomerId());
        accounts.setAccountType("SAVINGS");
        accounts.setBranchAddress("201 Main Road, London");
//        accounts.setCreatedAt(LocalDateTime.now());
//        accounts.setCreatedBy("Anonymous");
        return accounts;

    }

    @Override
    public CustomerDto fetchDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString())
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdate = false;
        if(customerDto != null){
            Customer customer = customerRepository.findByMobileNumber(customerDto.getMobileNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "mobileNumber", customerDto.getMobileNumber())
            );
            Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                    () -> new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString())
            );

            Accounts updatedAccount = AccountsMapper.mapToAccounts(customerDto.getAccountsDto(), accounts);
            Customer updatedCustomer = CustomerMapper.mapToCustomer(customerDto, customer);
            accountsRepository.save(updatedAccount);
            customerRepository.save(updatedCustomer);
            isUpdate = true;
        }

        return isUpdate;
    }

    @Override
    public void updateCommunicationStatus(Long accountNumber) {
        Accounts accounts = accountsRepository.findByAccountNumber(accountNumber).orElseThrow(
                () -> new ResourceNotFoundException("Accounts", "accountNumber", accountNumber.toString())
        );
        accounts.setCommunicationStatus(true);
        accountsRepository.save(accounts);
        logger.info("Communication status changed successfully!!!");
    }


}
