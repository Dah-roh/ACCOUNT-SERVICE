package com.reloadly_task.accountsService.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
@Data
public class AccountRegistrationRequest {

    @NotBlank(message = "please enter a valid email")
    private int accountNumber;

    @NotBlank(message = "please enter a valid email")
    private Long userId;

    @NotBlank(message = "please enter a valid email")
    private String username;

    @NotBlank(message = "please enter a valid email")
    private BigDecimal accountBalance;

    @NotBlank(message = "please enter a valid email")
    private String accountType;

    private boolean block;

}
