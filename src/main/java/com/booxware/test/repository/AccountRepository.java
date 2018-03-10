package com.booxware.test.repository;

import com.booxware.test.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.time.LocalDateTime;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findAccountByusername(String userName);
    Account findByUsernameEqualsAndLastLoginAfter(String userName,Date timeStamp);
    Account findByUsernameEqualsAndEncryptedPasswordEquals(String username,String clearTextpassword);
    Account findByUsername(String username);
}
