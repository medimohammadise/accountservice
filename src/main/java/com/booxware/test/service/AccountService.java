package com.booxware.test.service;

import com.booxware.test.domain.Account;
import com.booxware.test.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service("accountService")
public class AccountService implements AccountServiceInterface {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account login(String username, String password) {
        return null;
    }

    @Override
    public Account register(String username, String email, String password) {
        Account account=new Account(username,password.getBytes(),"Mr",email,null);
        return accountRepository.save(account);
    }




    @Override
    public void deleteAccount(String username) {

    }

    @Override
    public boolean hasLoggedInSince(Date date) {
        return false;
    }
}
