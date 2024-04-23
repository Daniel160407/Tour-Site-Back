package com.toursiteback.service.adminPanel;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@Getter
public class AdminProvider {
    @Value("${adminEmail}")
    private String adminEmail;
    @Value("${adminPassword}")
    private String adminPassword;

    private final Environment environment;

    @Autowired
    public AdminProvider(Environment environment) {
        this.environment = environment;
    }
}
