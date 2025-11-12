package com.cristina.notificationTemplateFormatter.model;

public record TemplateResponse(
    Status status,
    String statusDescription,
    String emailContent,
    String smsContent,
    String emailSubject
) {
}
