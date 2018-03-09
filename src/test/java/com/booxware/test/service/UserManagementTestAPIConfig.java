package com.booxware.test.service;

import org.easymock.EasyMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserManagementTestAPIConfig {
    @Bean(name = "accountService")
    public AccountServiceInterface getAppUserService() {
        return EasyMock.mock(AccountService.class);
    }
}
