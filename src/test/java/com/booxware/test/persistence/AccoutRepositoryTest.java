package com.booxware.test.persistence;

import com.booxware.test.config.PersistenceConfiguration;
import com.booxware.test.domain.Account;
import com.booxware.test.repository.AccountRepository;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {PersistenceConfiguration.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccoutRepositoryTest {
    @Autowired
    AccountRepository accountRepository;

    MessageDigestPasswordEncoder encoder ;

    @Autowired
    ApplicationContext applicationContext;

    @Before
    public void setUp() throws Exception {
        encoder = new Md5PasswordEncoder();  //we need to create instance ourselve becuase spring context is not available here
    }

    @Test
    public void testCreateAccount(){
        Account account=new Account("test1",encoder.encodePassword("test1","test1".toUpperCase()+"@").toString() ,"Mr","test1@tipico.com",null);
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
        Account account= accountRepository.findByUsernameEqualsAndEncryptedPasswordEquals ("test1",encoder.encodePassword("test1","test1".toUpperCase()+"@"));
        assertNotNull(account);
        Calendar calendar=Calendar.getInstance();
        if (account!=null)
            account.setLastLogin(new Timestamp(System.currentTimeMillis()));

        accountRepository.save(account);
    }

    @Test
    public void testLoginAfterTimeStamp(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Account account= accountRepository.findByUsernameEqualsAndLastLoginAfter ("test1",new Timestamp(cal.getTime().getTime()));
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
