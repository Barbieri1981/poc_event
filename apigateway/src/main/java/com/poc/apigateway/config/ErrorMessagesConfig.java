package com.poc.apigateway.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "error-messages")
public class ErrorMessagesConfig {
    private ErrorMessage unauthorized;

    @Data
    @NoArgsConstructor
    public static class ErrorMessage {
        private String code;
        private String message;
    }
}

