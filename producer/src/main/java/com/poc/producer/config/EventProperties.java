package com.poc.producer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "spring.kafka.template")
@Getter
@Setter
public class EventProperties {
    private String defaultTopic;
}
