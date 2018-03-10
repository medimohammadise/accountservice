package com.booxware.test.service;

import com.booxware.test.domain.Account;
import com.booxware.test.exception.AccountServiceException;
import com.booxware.test.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service("accountService")
public class AccountService implements AccountServiceInterface {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    MessageDigestPasswordEncoder encoder;

    @Override
    public Account login(String username, String password) {
        Account loggedInAccount = accountRepository.findByUsernameEqualsAndEncryptedPasswordEquals(username, encoder.encodePassword(password, username.toUpperCase() + "@").getBytes());
        Calendar calendar = Calendar.getInstance();
        if (loggedInAccount != null) {
            loggedInAccount.setLastLogin(new Date(calendar.getTime().getTime()));
            List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
            list.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, password,list);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        else
            throw new AccountServiceException("username or password is not valid");
        return accountRepository.save(loggedInAccount);
    }

    @Override
    public Account register(String username, String email, String password) {
        Account account = new Account(username, encoder.encodePassword(password, username.toUpperCase() + "@").getBytes(), "Mr", email, null);
        account = accountRepository.save(account);
        return account;
    }

    @Override
    public void deleteAccount(String username) {
        Account account = accountRepository.findAccountByusername(username);
        if (account != null)
            accountRepository.delete(account);
    }

    @Override
    public boolean hasLoggedInSince(Date date) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication==null)
            throw new AccountServiceException("You need to login for using this service");
        Account account = accountRepository.findByUsernameEqualsAndLastLoginAfter(authentication.getName() , date);
        return account != null;
    }

    @Override
    public List<Account> getAllUserAccounts() throws AccountServiceException {
        return  accountRepository.findAll();
    }

    @Override
    public boolean checkUserExists(String userName) {
        return accountRepository.findByUsername(userName)!=null;
    }


}
