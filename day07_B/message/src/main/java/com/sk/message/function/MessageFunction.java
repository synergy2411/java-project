package com.sk.message.function;

import com.sk.message.dto.AccountsMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunction {

    private static final Logger logger = LoggerFactory.getLogger(MessageFunction.class);

    @Bean
    public Function<AccountsMsgDto, AccountsMsgDto> email(){
        return (accountsMsgDto) -> {
            logger.info("Sending email with the details - {}", accountsMsgDto);
            return accountsMsgDto;
        };
    }

    @Bean
    public Function<AccountsMsgDto, Long> sms(){
        return accountsMsgDto -> {
            logger.info("Sending SMS with details - {}", accountsMsgDto.mobileNumber());
            return accountsMsgDto.accountNumber();
        };
    }

}
