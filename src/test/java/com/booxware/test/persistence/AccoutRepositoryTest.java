package com.booxware.test.persistence;

import com.booxware.test.config.PersistenceConfiguration;
import com.booxware.test.config.ServiceConfiguration;
import com.booxware.test.domain.Account;
import com.booxware.test.repository.AccountRepository;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {PersistenceConfiguration.class,ServiceConfiguration.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccoutRepositoryTest {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    MessageDigestPasswordEncoder encoder;

    @Test
    public void testCreateAccount(){
        //accountService.register("test1","test1@gmail.com","1234");
        Account account=new Account("test1",encoder.encodePassword("test1","test1".toUpperCase()+"@").getBytes() ,"Mr","test1@tipico.com",null);
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
        Account account= accountRepository.findByUsernameEqualsAndEncryptedPasswordEquals ("test1",encoder.encodePassword("test1","test1".toUpperCase()+"@").getBytes());
        assertNotNull(account);
    }

    @Test
    public void testLoggedInAfterTimeStamp(){
        Account account= accountRepository.findByUsernameEqualsAndLastLoginAfter ("test1",LocalDateTime.now().minusDays(1));
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

    public static class AccoutServiceTest {
    }
}
