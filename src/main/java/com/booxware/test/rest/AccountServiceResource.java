package com.booxware.test.rest;

import com.booxware.test.domain.Account;
import com.booxware.test.rest.dto.AccountResource;
import com.booxware.test.rest.dto.LoginResource;
import com.booxware.test.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequestMapping("/api/account")
public class AccountServiceResource {
    @Autowired
    AccountService accountService;

    @RequestMapping(value="login",method = {RequestMethod.POST}, produces = {APPLICATION_JSON_VALUE}, consumes = {APPLICATION_JSON_VALUE})
    public @ResponseBody Account login(@RequestBody LoginResource loginResource){
        return accountService.login(loginResource.getUsername(),loginResource.getPassword());
    }

    @RequestMapping(value="register",method = {RequestMethod.POST}, produces = {APPLICATION_JSON_VALUE}, consumes = {APPLICATION_JSON_VALUE})
    public @ResponseBody Account registerAccount(@RequestBody AccountResource accountResource){
        return accountService.register(accountResource.getUsername(),accountResource.getEmail(),accountResource.getPassword());
    }

    @RequestMapping(value="listAll",method = {RequestMethod.GET}, produces = {APPLICATION_JSON_VALUE}, consumes = {APPLICATION_JSON_VALUE})
    public @ResponseBody List<Account> listAllAccounts() {
        return accountService.getAllUserAccounts();

    }
    @RequestMapping(value="delete/{userName}",method = {RequestMethod.DELETE}, produces = {APPLICATION_JSON_VALUE}, consumes = {APPLICATION_JSON_VALUE})
    public @ResponseBody boolean  deleteAccounts(@PathVariable String userName) {
        accountService.deleteAccount(userName);
        return accountService.checkUserExists(userName);
    }

    @RequestMapping(value="loggedInAfter",method = {RequestMethod.GET}, produces = {APPLICATION_JSON_VALUE}, consumes = {APPLICATION_JSON_VALUE})
    public @ResponseBody Boolean  isloggedInAfter() {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        return accountService.hasLoggedInSince(new Date(calendar.getTime().getTime() ));


    }


}
