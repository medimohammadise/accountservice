package com.booxware.test.repository;

import com.booxware.test.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Date;

public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findAccountByusername(String userName);
    Account findByUsernameEqualsAndLastLoginAfter(String userName,LocalDate timeStamp);
    Account findByUsernameEqualsAndEncryptedPasswordEquals(String username,byte[] clearTextpassword);
}
