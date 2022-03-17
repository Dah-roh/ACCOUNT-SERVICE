package com.reloadly_task.accountsService.dto.response;

import com.reloadly_task.accountsService.model.Account;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class AccountRegisterResponse {

    private ZonedDateTime timestamp;

    private String message;

    private boolean status;

    private Account data;
}
