package com.booxware.test.service;

import org.easymock.EasyMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

@Configuration
public class UserManagementTestAPIConfig {
    @Bean(name = "accountService")
    public AccountServiceInterface getAccountService() {

        return EasyMock.mock(AccountService.class);
    }



}
