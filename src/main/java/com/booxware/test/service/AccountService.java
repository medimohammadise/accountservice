package com.booxware.test.service;

import com.booxware.test.domain.Account;
import com.booxware.test.exception.AccountServiceException;
import com.booxware.test.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;

@Service("accountService")
public class AccountService implements AccountServiceInterface {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    MessageDigestPasswordEncoder encoder;
    @Override
    public Account login(String username, String password) {

        Account loggedInAccount= accountRepository.findByUsernameEqualsAndEncryptedPasswordEquals(username,encoder.encodePassword(password,username.toUpperCase()+"@").getBytes());
        Calendar calendar = Calendar.getInstance();
        if (loggedInAccount!=null)
            loggedInAccount.setLastLogin(new Date(calendar.getTime().getTime()));
        else
            throw new AccountServiceException("username or password is not valid");
        return accountRepository.save(loggedInAccount);
    }

    @Override
    public Account register(String username, String email, String password) {


        Account account=new Account(username,encoder.encodePassword(password,username.toUpperCase()+"@").getBytes(),"Mr",email,null);
        account= accountRepository.save(account);
        return account;
    }




    @Override
    public void deleteAccount(String username) {
        Account account= accountRepository.findAccountByusername(username);
        if (account!=null)
            accountRepository.delete(account);

    }

    @Override
    public boolean hasLoggedInSince(Date date) {

        Account account= accountRepository.findByUsernameEqualsAndLastLoginAfter("user2",date);
        return account!=null;
    }
}
