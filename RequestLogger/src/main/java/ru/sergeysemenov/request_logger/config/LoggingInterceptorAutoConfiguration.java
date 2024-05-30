package ru.sergeysemenov.request_logger.config;

import org.springframework.boot.autoconfigure.AutoConfiguration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@AutoConfiguration
@EnableConfigurationProperties(RequestLoggerProperties.class)
@ConditionalOnProperty(prefix = "request-logger", value = "active", havingValue = "true")
public class LoggingInterceptorAutoConfiguration  {

    @Bean
    public LoggingInterceptor loggingInterceptor() {
        return new LoggingInterceptor();
    }

    @Bean
    @ConditionalOnBean(name = "loggingInterceptor")
    public WebMvcConfigurer webMvcConfigurer(LoggingInterceptor loggingInterceptor) {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(loggingInterceptor);
            }
        };
    }

}