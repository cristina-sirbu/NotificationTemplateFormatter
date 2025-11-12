package com.cristina.notificationTemplateFormatter.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class TemplatesRequest {
    private String notificationTemplateName;
    private String notificationMode;
    private List<NotificationParameter> notificationParameters;
}
