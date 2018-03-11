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
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;

import static junit.framework.Assert.assertEquals;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceConfiguration.class,PersistenceConfiguration.class})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountServiceTest {
    @Autowired
    private WebApplicationContext webApplicationContext; // cached

    @Autowired
    AccountServiceInterface accountService;

    @Autowired
    AccountRepository accountRepository;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void testCreateAccount(){
        Account newAccount=createMock(Account.class);
        newAccount= accountService.register("user2","user2@tipico.com","user2");
        assertEquals("user2",newAccount.getUsername());
    }
    @Test
    public void testLoginAccount(){
        Account loggedInAccount=createMock(Account.class);
        loggedInAccount=accountService.login("user2","user2");
        assertEquals("user2",loggedInAccount.getUsername());
    }
    @Test
    public void testLogonAfter(){

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        boolean isLoggedIn=accountService.hasLoggedInSince(new Timestamp(cal.getTime().getTime()));
        assertEquals(true,isLoggedIn);
    }
    @Test
    public void testUnRegisterAccount(){
       accountService.deleteAccount("user2");
       Account account= accountRepository.findAccountByusername("user2");
       assertNull(account);
    }


}