package com.reloadly_task.accountsService.controller;

import com.reloadly_task.accountsService.dto.request.AccountRegistrationRequest;
import com.reloadly_task.accountsService.dto.response.UpdateAccountResponse;
import com.reloadly_task.accountsService.model.Account;
import com.reloadly_task.accountsService.service.AccountService;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    private final Logger log = LoggerFactory.logger(this.getClass());

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("register")
    public ResponseEntity<?> register (@RequestBody AccountRegistrationRequest accountRegistrationRequest){
        return new ResponseEntity<>(accountService.registerNewAccount(accountRegistrationRequest),HttpStatus.OK);
    }

    @PutMapping("update/{id}/{balance}")
    public ResponseEntity<?> updateBalance (@PathVariable("balance") BigDecimal balance,  @PathVariable("id") Long id){
        UpdateAccountResponse updateAccountResponse = new UpdateAccountResponse();
        try{
            updateAccountResponse = accountService.updateAccountDetails(balance, id);

        }catch (Exception e){
            log.info("Can not update transaction status: "+e.getMessage());
        }
        return new ResponseEntity<>(updateAccountResponse,HttpStatus.OK);
    }

    @GetMapping("details/{id}")
    public String getBalance (@PathVariable("id") Long id){
        return accountService.getAccountBalanceByUserId(id).toString();
    }

    @GetMapping("/")
    public List<Account> getAllAccounts (){
        return accountService.getAllAccounts();
    }

}

