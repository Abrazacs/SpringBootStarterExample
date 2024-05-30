package ru.sergeysemenov.request_logger.config;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "request-logger")
public record RequestLoggerProperties(boolean active) {}
