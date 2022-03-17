package com.reloadly_task.accountsService.service;

import com.reloadly_task.accountsService.dto.request.AccountRegistrationRequest;
import com.reloadly_task.accountsService.dto.response.AccountRegisterResponse;
import com.reloadly_task.accountsService.dto.response.UpdateAccountResponse;

import java.math.BigDecimal;

public interface AccountService {

    AccountRegisterResponse registerNewAccount(AccountRegistrationRequest accountRegistrationRequest);
    UpdateAccountResponse updateAccountDetails(BigDecimal balanceRequest, long id);
    BigDecimal getAccountBalanceByUserId(Long id);
    void transactionService(String s, Long id);
}
