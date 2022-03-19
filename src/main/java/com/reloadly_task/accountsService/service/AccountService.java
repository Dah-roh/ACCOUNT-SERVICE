package com.reloadly_task.accountsService.service;

import com.reloadly_task.accountsService.dto.request.AccountRegistrationRequest;
import com.reloadly_task.accountsService.dto.response.AccountRegisterResponse;
import com.reloadly_task.accountsService.dto.response.UpdateAccountResponse;
import com.reloadly_task.accountsService.model.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    AccountRegisterResponse registerNewAccount(AccountRegistrationRequest accountRegistrationRequest);
    UpdateAccountResponse updateAccountDetails(BigDecimal balanceRequest, long id);
    BigDecimal getAccountBalanceByUserId(Long id);
    List<Account> getAllAccounts();
    void transactionService(String s, Long id);
}
