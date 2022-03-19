package com.reloadly_task.accountsService.service.impl;

import com.reloadly_task.accountsService.dto.request.AccountRegistrationRequest;
import com.reloadly_task.accountsService.dto.response.AccountRegisterResponse;
import com.reloadly_task.accountsService.dto.response.UpdateAccountResponse;
import com.reloadly_task.accountsService.model.Account;
import com.reloadly_task.accountsService.repository.AccountRepository;
import com.reloadly_task.accountsService.service.AccountService;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private  WebClient webClient;
    @Value("${transaction.service") private  String url;
    private final Logger log = LoggerFactory.logger(this.getClass());

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountRegisterResponse registerNewAccount(AccountRegistrationRequest accountRegistrationRequest) {
        new Account();
        Account account;
        ModelMapper modelMapper = new ModelMapper();
        account = modelMapper.map(accountRegistrationRequest, Account.class);
        try{
            AccountRegisterResponse accountRegisterResponse = new AccountRegisterResponse();
           Account account1 = accountRepository.save(account);
           accountRegisterResponse.setData(account1);
           accountRegisterResponse.setMessage("Account Created Successfully");
           accountRegisterResponse.setStatus(true);
           return accountRegisterResponse;
        } catch (Exception e) {
            log.info("Error in registering new account : "+e.getCause());
        }
        AccountRegisterResponse accountRegisterResponse = new AccountRegisterResponse();
        accountRegisterResponse.setData(null);
        accountRegisterResponse.setMessage("Account failed to create");
        accountRegisterResponse.setStatus(false);
        return accountRegisterResponse;
    }

    public void transactionService(String uri, Long id) {
        WebClient webClient = WebClient.create(url);
       webClient.get()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(String.class).block();
    }

    @Override
    public UpdateAccountResponse updateAccountDetails(BigDecimal balance, long id) {
            UpdateAccountResponse updateAccountResponse = new UpdateAccountResponse();
        try{
            Optional<Account> account = accountRepository.findByUserId(id);
            if (account.isPresent()){
                Account accountDetails = account.get();
                accountDetails.setAccountBalance(balance);
                        updateAccountResponse.setData(
                                accountRepository.save(accountDetails));
                        updateAccountResponse.setMessage("ACCOUNT UPDATE SUCCESSFUL");
                        updateAccountResponse.setStatus(true);
                return updateAccountResponse;
            }
        }catch (Exception e) {
            log.info("Error updating Account balance: "+e.getMessage(), e.getCause());
        }
        updateAccountResponse.setData(null);
        updateAccountResponse.setMessage("ACCOUNT UPDATE FAILED");
        updateAccountResponse.setStatus(false);
        return updateAccountResponse;
    }

    @Override
    public BigDecimal getAccountBalanceByUserId(Long id) {
        Account account = accountRepository.findByUserId(id).get();
        System.out.println("account balance id: "+account.getAccountBalance());
        return account.getAccountBalance();
    }
}
