package com.booxware.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

@Configuration
@PropertySource("classpath:usermanagement.properties")
public class ServiceConfiguration {
    @Bean
    MessageDigestPasswordEncoder getMessageDigestpasswordEncoder(){
        return  new Md5PasswordEncoder();
    }
}
