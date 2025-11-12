package com.cristina.notificationTemplateFormatter.services;

import com.cristina.notificationTemplateFormatter.model.NotificationParameter;
import com.cristina.notificationTemplateFormatter.model.Status;
import com.cristina.notificationTemplateFormatter.model.TemplateResponse;
import com.cristina.notificationTemplateFormatter.model.TemplatesRequest;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TemplateService {

    private final SpringTemplateEngine templateEngine;

    public TemplateService(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public TemplateResponse createTemplatedNotification(TemplatesRequest templatesRequest) {
        Map<String, String> notificationParametersMap =
                templatesRequest.getNotificationParameters().
                        stream()
                        .collect(Collectors
                                .toMap(NotificationParameter::getNotificationParameterName, NotificationParameter::getNotificationParameterValue));

        if (templatesRequest.getNotificationMode().equals("EMAIL")) {
            return new TemplateResponse(
                    Status.SUCCESS,
                    "Successfully merged the template with the template parameters",
                    getEmailTemplate(notificationParametersMap.get("name"),
                            notificationParametersMap.get("oldPhoneNumber"),
                            notificationParametersMap.get("newPhoneNumber")),
                    null,
                    "Phone Number has been changed."
            );
        } else if (templatesRequest.getNotificationMode().equals("SMS")) {
            return new TemplateResponse(
                    Status.SUCCESS,
                    "Successfully merged the template with the template parameters",
                    null,
                    getSMSTemplate(
                            notificationParametersMap.get("name"),
                            notificationParametersMap.get("accountnumber"),
                            notificationParametersMap.get("balance")
                    ),
                    null
            );
        } else {
            return new TemplateResponse(
                    Status.FAILED,
                    "Failed to create a templated notification of type "+templatesRequest.getNotificationMode(),
                    null,
                    null,
                    null);
        }
    }

    public String getEmailTemplate(String name, String oldPhoneNumber, String newPhoneNumber) {
        Context ctx = new Context();
        ctx.setVariable("name", name);
        ctx.setVariable("oldPhoneNumber", oldPhoneNumber);
        ctx.setVariable("newPhoneNumber", newPhoneNumber);
        return templateEngine.process("email/PhoneNumberChanged", ctx);
    }

    public String getSMSTemplate(String name, String accountnumber, String balance) {
        Context ctx = new Context();
        ctx.setVariable("name", name);
        ctx.setVariable("accountnumber", accountnumber);
        ctx.setVariable("balance", balance);
        return templateEngine.process("email/ViewBalance", ctx);
    }
}
