package com.reloadly_task.accountsService.dto.response;

import com.reloadly_task.accountsService.model.Account;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;


@Data
public class UpdateAccountResponse {
    private ZonedDateTime timestamp;
    private String message;
    private boolean status;
    private Account data;
}
