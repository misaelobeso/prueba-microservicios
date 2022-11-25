package com.microservice.test.application;

import com.microservice.test.entity.AccountEntity;
import com.microservice.test.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/get")
    public Iterable<AccountEntity> get() {
        return this.accountService.findAll();
    }
}
