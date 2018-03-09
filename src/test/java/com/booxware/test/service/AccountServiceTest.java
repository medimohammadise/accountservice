package com.booxware.test.service;

import com.booxware.test.config.PersistenceConfiguration;
import com.booxware.test.config.ServiceConfiguration;
import com.booxware.test.domain.Account;
import com.booxware.test.repository.AccountRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {UserManagementTestAPIConfig.class,PersistenceConfiguration.class,ServiceConfiguration.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepository;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void testCreateAccount(){
        Account newAccount=createMock(Account.class);
        expect(accountService.register("user2","user2@tipico.com","user2")).andReturn(newAccount).once();
        replay(newAccount);
    }
    @Test
    public void testLoginAccount(){
        Account loggedInAccount=createMock(Account.class);
        expect(accountService.login("user2","user2") ).andReturn(loggedInAccount).once();
        replay(loggedInAccount);
    }
    @Test
    public void testLogonAfter(){

       expect(accountService.hasLoggedInSince(LocalDateTime.now().minusDays(1))).andReturn(true);

    }
    @Test
    public void testUnRegisterAccount(){
       accountService.deleteAccount("user2");
       Account account= accountRepository.findAccountByusername("user2");
       assertNull(account);
    }


}