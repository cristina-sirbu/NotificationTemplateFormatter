package com.cristina.notificationTemplateFormatter.controllers;

import com.cristina.notificationTemplateFormatter.model.TemplateResponse;
import com.cristina.notificationTemplateFormatter.model.TemplatesRequest;
import com.cristina.notificationTemplateFormatter.services.TemplateService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications/templates")
public class TemplatesController {

    private final TemplateService templateService;

    public TemplatesController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @GetMapping
    public TemplateResponse getTemplates(@RequestBody @Valid TemplatesRequest request, Model model) {
        return templateService.createTemplatedNotification(request);
    }
}
