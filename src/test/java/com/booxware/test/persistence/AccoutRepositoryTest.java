package com.booxware.test.persistence;

import com.booxware.test.config.PersistenceConfiguration;
import com.booxware.test.domain.Account;
import com.booxware.test.repository.AccountRepository;
import com.booxware.test.service.AccountServiceInterface;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = PersistenceConfiguration.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccoutRepositoryTest {
    @Autowired
    AccountRepository accountRepository;
    @Test
    public void testCreateAccount(){
        //accountService.register("test1","test1@gmail.com","1234");
        Account account=new Account("test1","1234".getBytes(),"Mr","test1@tipico.com",null);
        accountRepository.save(account);
    }
    @Test
    public void testGetAllAccounts(){
        List<Account> userAccountList= accountRepository.findAll();
        assertEquals(true,userAccountList.size()>0);
        assertEquals("test1", userAccountList.get(1).getUsername());
    }

    @Test
    public void testLogin(){
        Account account= accountRepository.findByUsernameEqualsAndEncryptedPasswordEquals ("test1","1".getBytes());
        assertNotNull(account);
    }

    @Test
    public void testLoggedInAfterTimeStamp(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String date = "16/08/2018";

        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(date, formatter);

        Account account= accountRepository.findByUsernameEqualsAndLastLoginAfter ("test1",localDate);
        assertNotNull(account);

    }

    @Test
    public void testUnRegisterAccount(){

        Account account= accountRepository.findAccountByusername("test1");
        assertNotNull(account);
        accountRepository.delete(account);
        Account deleted=accountRepository.findAccountByusername("test1");
        assertNull(deleted);
    }
}
