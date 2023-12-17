package com.poc.producer.config;

import com.poc.producer.dto.ErrorConfig;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "errors")
public class ErrorProperties {
    private ErrorConfig conversionJson;
}