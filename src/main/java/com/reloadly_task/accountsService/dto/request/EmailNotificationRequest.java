package com.reloadly_task.accountsService.dto.request;

import lombok.Data;

@Data
public class EmailNotificationRequest {

    private String from;
    private String to;

    private String subject;

    private String narration;
}
