package com.ayd2.imporcomgua.properties.smtp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "spring.mail")
@Getter
@Setter
public class SmtpProperties {
    private String username;
    private String password;
}
