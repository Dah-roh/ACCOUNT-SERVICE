package com.reloadly_task.accountsService.model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.math.BigDecimal;

@Entity
@Data
public class Account extends GeneralBaseModel {

    @Column(unique = true)
    private int accountNumber;

    @Column(unique = true, nullable = false)
    private Long userId;

    @Email(message = "please enter a valid email")
    @Column(unique = true)
    private String username;

    private BigDecimal accountBalance;

    private String accountType;
    private boolean block;
}
