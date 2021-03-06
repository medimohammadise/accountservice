package com.booxware.test.rest;

import com.booxware.test.config.SecurityConfig;
import com.booxware.test.config.ServiceConfiguration;
import com.booxware.test.rest.dto.AccountResource;
import com.booxware.test.rest.dto.LoginResource;
import com.booxware.test.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Calendar;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ServiceConfiguration.class, SecurityConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AccountResourceTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext; // cached


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).alwaysDo(MockMvcResultHandlers.print()).build();
    }

    @Autowired
    AccountService accountService;


    @Test
    public void testCreateAccount() throws Exception {
        ObjectMapper mapper=new ObjectMapper();
        AccountResource accountResource =new AccountResource();
        accountResource.setUsername("user3");
        accountResource.setPassword("user3");
        accountResource.setEmail("user3@tipico.com");
        MvcResult result  = mockMvc.perform(post("/api/account/register")

                .contentType(MediaType.APPLICATION_JSON)

                .content(mapper.writeValueAsString(accountResource)))

                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();



    }
    @Test
    public void testLoginAccount() throws Exception  {
        ObjectMapper mapper=new ObjectMapper();
        LoginResource loginResource =new LoginResource();
        loginResource.setUsername("user3");
        loginResource.setPassword("user3");
        MvcResult result  = mockMvc.perform(post("/api/account/login")

                .contentType(MediaType.APPLICATION_JSON)

                .content(mapper.writeValueAsString(loginResource)))

                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testLogonAfter() throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        MvcResult result  = mockMvc.perform(get("/api/account/loginAfter/"+cal.getTime().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
        System.out.print(result.getResponse().getContentAsString());
       // assertEquals(true, result.getResponse().toString());
    }

    @Test
    public void testUnRegisterAccount()throws Exception  {
        MvcResult result  = mockMvc.perform(delete("/api/account/delete/user3")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

}