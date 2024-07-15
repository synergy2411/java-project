package com.ukg.accounts.function;

import com.ukg.accounts.service.IAccountsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class AccountsFunction {

    private static final Logger logger = LoggerFactory.getLogger(AccountsFunction.class);

    @Bean
    public Consumer<Long> updateCommunicationStatus(IAccountsService iAccountsService){
        return accountNumber -> {
            logger.info("About to update communication status");
            iAccountsService.updateCommunicationStatus(accountNumber);
        };
    }
}
